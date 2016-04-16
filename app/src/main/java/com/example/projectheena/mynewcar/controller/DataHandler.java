package com.example.projectheena.mynewcar.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.projectheena.mynewcar.servicehandler.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by projectheena on 5/4/16.
 */
public class DataHandler {

    ImageLoader imageLoader;
    JSONArray jsonArray;
    public static List<Bitmap>
            images=null;
    public DataHandler()
    {
     imageLoader= new ImageLoader();
        images=new ArrayList<>();
    }

    public List<Bitmap> getJsonData()
    {
       jsonArray= new JSONArray();


            jsonArray=imageLoader.getJsonArray();
            Log.e("Jsonarray","json array"+jsonArray.toString());

             for(int i=0;i<jsonArray.length();i++)
             {
              try {
                   JSONObject jsonObject=jsonArray.getJSONObject(i);
                   String imageUrl=jsonObject.optString("image_name").toString();
                   String image="http://images.mynewcar.in/uploads/slider/"+imageUrl;
                   images.add(downloadImage(image));
                  }
              catch (JSONException e)
              {
               e.printStackTrace();
              }
             }
        return images;
    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap=null;
        try {
            URL url = new URL(imageUrl);
            bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
