package com.rv.db;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rv.db.model.adapter.RVAdapter;
import com.rv.db.model.database.SaveInDatabase;
import com.rv.db.model.helper.Constants;
import com.rv.db.model.helper.Utils;
import com.rv.db.pojo.ChannelList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ProgressDialog pDialog;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url ="https://www.googleapis.com/youtube/v3/search?part=id,snippet&maxResults=20&channelId=UCCq1xDJMBRF61kiOgU90_kw&key=AIzaSyBRLPDbLkFnmUv13B-Hq9rmf0y7q8HOaVs";
    List<ChannelList> rlist = new ArrayList<>();
    List<ChannelList> dblist = new ArrayList<>();
    RVAdapter madapter;
    static boolean isDataLoaded = false;
    ChannelList mchannel = null;
    RunInBackground rback;
    public SaveInDatabase mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mdatabase = SaveInDatabase.getInstance(this);

        //rlist =  mdatabase.getallData();
        madapter = new RVAdapter(this,rlist);

        Log.d("rlist","inside onCReate()");

        mRecyclerView.setAdapter(madapter);
       sendRequest();
    }

    private void sendRequest() {

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        if (getNetworkAvailability()) {
            getFeed();
        //isDataLoaded = false;
        } else {

            Toast.makeText(getApplicationContext(),"NO Network Available" + mdatabase.numberOfRows(),Toast.LENGTH_LONG).show();
           getFeedFromDatabase();
        }
    }
    private void getFeed(){
// Creating volley request obj
        JsonObjectRequest mchannelReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                       // hidePDialog();
                        pDialog.dismiss();
//                        ChannelList mchannel = null;
//                        RunInBackground rback;
                        try {
                            JSONObject response1 = new JSONObject(response.toString());
                            JSONArray list = response1.getJSONArray("items");    // you have done this wrong
                            if (list.length() > 0) {
                               if (mdatabase.numberOfRows() > 0){
                                    mdatabase.deleteData();

                                }
                                // parsing json//wait what did yu got
                                for (int i = 0; i < list.length(); i++) {
                                    try {
                                        JSONObject obj = list.getJSONObject(i);

                                        mchannel = new ChannelList();
                                        mchannel.setTitle(obj.getJSONObject("snippet").getString("title"));


                                        JSONObject snippetJSONObject = obj.getJSONObject("snippet");
                                        mchannel.setDescription(snippetJSONObject.getString("description"));
                                        mchannel.setDatetime(snippetJSONObject.getString("publishedAt"));
                                        String img = snippetJSONObject.getJSONObject("thumbnails").getJSONObject("default").getString("url");

                                        mchannel.setThumbnailurl(img);
/*if(i == list.length()-1) {
    Log.d("test","i ="+i);
}*/
                                        rlist.add(mchannel);


                                        RunInBackground rback = new RunInBackground();
                                        rback.execute(mchannel);
                                        // isDataLoaded = true;
                                        // mdatabase.addFlower(mchannel);
                                        Log.d("rlist", "" + rlist);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                }

                            }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data

                        madapter.notifyDataSetChanged();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
               // hidePDialog();
                pDialog.dismiss();
            }
        });

        RequestQueue requestueue = Volley.newRequestQueue(this);
        requestueue.add(mchannelReq);
    }

        private void getFeedFromDatabase() {
//        dblist =  mdatabase.getallData();
//        madapter = new RVAdapter(this,dblist);
//        mRecyclerView.setAdapter(madapter);
//            pDialog.dismiss();

                rlist.clear();
                rlist =  mdatabase.getallData();
            madapter = new RVAdapter(this,rlist);
           mRecyclerView.setAdapter(madapter);
//                madapter.notifyDataSetChanged();
            //    pDialog.dismiss();
            }


    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getApplicationContext());
    }

    public class RunInBackground extends AsyncTask<ChannelList, Void, Void> {


        private final String TAG = RunInBackground.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // isDataLoaded = true;
        }

        @Override
        protected Void doInBackground(ChannelList... params) {

            ChannelList flower = params[0];

            try {

                    mdatabase.addFlower(flower);//this one

            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            return null;
        }

    }
}
