package com.example.projectheena.mynewcar.servicehandler;

import android.util.Log;

import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by projectheena on 5/4/16.
 */
public class ImageLoader {

    JSONArray jsonData;
    //method read Json and stores in jsonArray
    public JSONArray getJsonArray()
    {

        InputStream contentInfoDataStream;
        StringBuffer ContentInfoDataBuffer = new StringBuffer();
        String data;

        try {
            URL contentInfoDataUrl = new URL("https://mynewcar.in/api/slider/format/json/city/1");
            HttpURLConnection connection = (HttpURLConnection) contentInfoDataUrl.openConnection();
            contentInfoDataStream = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(contentInfoDataStream));
            while ((data= in.readLine()) != null)
            {
                ContentInfoDataBuffer.append(data);
            }
             jsonData=new JSONArray(ContentInfoDataBuffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}
