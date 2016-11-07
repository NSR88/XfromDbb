package com.rv.db.model.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rv.db.MainActivity;
import com.rv.db.R;
import com.rv.db.pojo.ChannelList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mc11 on 2/11/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>{
    Activity mcontext;
    List<ChannelList> mchannelList = new ArrayList<>();


    public RVAdapter(Activity mainActivity, List<ChannelList> rlist) {
       this. mcontext = mainActivity;
        this. mchannelList = rlist;

    }

    @Override
    public RVAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rview = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, null,false);
        return new MyViewHolder(rview);
    }

    @Override
    public void onBindViewHolder(final RVAdapter.MyViewHolder holder, int position) {
        ChannelList currchannel = mchannelList.get(position);
        holder.title.setText(currchannel.getTitle());
        holder.rating.setText(currchannel.getDescription());
        holder.year.setText(currchannel.getDatetime());

       // Picasso.with(mcontext).load(Utils.BitMapToString(currchannel.getThumbnailurl())).into(holder.thumbnail);
//        Log.d("picasso",Utils.BitMapToString(currchannel.getThumbnailurl()));
        /* Glide.with(mcontext)
                .load(currchannel.getThumbnailurl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(100,100) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        holder.thumbnail.setImageBitmap(resource); // Possibly runOnUiThread()
                    }
                });*/
        Glide.with(mcontext)
                .load(currchannel.getThumbnailurl())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mchannelList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        private ImageView thumbnail;
        private TextView title,rating,year,genre;


        public MyViewHolder(View itemView) {
            super(itemView);


            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
             title = (TextView) itemView.findViewById(R.id.title);
             rating = (TextView) itemView.findViewById(R.id.des);
             genre = (TextView) itemView.findViewById(R.id.genre);
            year = (TextView) itemView.findViewById(R.id.releaseYear);
        }
    }
}
