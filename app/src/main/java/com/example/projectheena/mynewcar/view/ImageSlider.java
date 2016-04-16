package com.example.projectheena.mynewcar.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.projectheena.mynewcar.R;
import com.example.projectheena.mynewcar.controller.DataHandler;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by projectheena on 2/4/16.
 * purpose:
 *
 * 1. this class consist the data required for image slider
 *
 */

public class ImageSlider extends PagerAdapter
{
    Context context;
    ImageView slide;

    DataHandler mDataHandler;
    HomeScreen mHomeScreen;
    ProgressDialog mProgressDialog;
    List<Bitmap> imagesForSlider=null;
    int images[]={R.drawable.imageslider,R.drawable.imagesliderone,
            R.drawable.imageslidertwo,R.drawable.imagesliderthree};
    public ImageSlider(HomeScreen homeScreen)
    {
        this.context=homeScreen;
        mDataHandler= new DataHandler();
        mHomeScreen= new HomeScreen();
        imagesForSlider= new ArrayList<>();
        imagesForSlider=mDataHandler.images;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {

        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.image_slider, container, false);
        slide= (ImageView) view.findViewById(R.id.imageSlider);
        slide.setImageBitmap(imagesForSlider.get(position));

//        slide.setImageResource(images[position]);
        ((ViewPager)container).addView(view);
        return view;
    }


    @Override
    public int getCount() {
        return imagesForSlider.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((View)object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }


}
