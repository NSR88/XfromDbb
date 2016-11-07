package com.rv.db.model.helper;

/**
 * Created by mc11 on 3/11/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static byte[] getPictureByteOfArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap getBitmapFromByte(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
// this method needs to be replaced // base64 and url are different thingss // now lets google how to get bitmap from url//url is string? yes ok
    public static Bitmap StringToBitMap(String encodedString) {
        Bitmap image = null;
        try { // BRB, I have to help you :) will be back ok // in 20-30 mins // u can disconnect tv for now  o//k BYE// and if it fixes then let me know ok ?Okji
             URL url = new URL(encodedString); // where is async task?
             image = BitmapFactory.decodeStream(url.openConnection().getInputStream()); // carry on image not null
        } catch(IOException e) { //say // y stepover not showing // ok // let me try // yes, it will initially be null, but on the next line, it will be updated//ohk
            System.out.println(e);
        } catch (Exception e) {
            e.printStackTrace(); // ask me doubt// y stepover is not highlihted // debugging probably stopped // re-debug ok// look data in db
        }// is tht in bitmap nw //yes how you knw:P u got? no // ok, first of all, its bitmap class object ok? yes, then from debug
        // its also showing android.graphics.Bitmap haan // :O o // now all done? no need to get data from db nd display it in RV // i ll do it later
        //why why? when no network ot shuld load from db //that I know // so should we do now? no i have to cook //
        // ok :) i ll continue later // ok 14 miscalls :P :O :D i am nt picking call :D u need to callback them all now
        // not all my dad :D
        // tmr some function he s calling me i told am nt cmng why re ?Dad agreed siblings torturing thy r calling my bro
        // ok so u won't go?no i wont okie, now go, cook food//, o k soon be ready to come for dinner :P lol, can I cm ryt now? yes you can
        //:D ok ji so nice of u :P BYE FOR NOW // BYE ladki :D
        return image;
    }
    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}