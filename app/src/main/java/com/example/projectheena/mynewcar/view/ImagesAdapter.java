package com.example.projectheena.mynewcar.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectheena.mynewcar.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by projectheena on 2/4/16.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MncHolder>
{
    LayoutInflater inflater;
    List<CarList> mncImages= Collections.emptyList();
    Context mContext;

    public ImagesAdapter(HomeScreen homeScreen, List<CarList> carLists) {
        inflater= LayoutInflater.from(homeScreen);
        mncImages=carLists;
        this.mContext=homeScreen;
    }

    @Override
    public MncHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.mncimages,parent,false);
        MncHolder mncHolder= new MncHolder(view);
        return mncHolder;
    }

    @Override
    public void onBindViewHolder(MncHolder holder, int position) {
        final CarList carList=mncImages.get(position);
        holder.mncImage.setImageResource(carList.mCarIcon);
        if(position==0) {
            holder.mncImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TestDrive.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mncImages.size();
    }

    public class MncHolder extends RecyclerView.ViewHolder
    {
        ImageView mncImage;
        public MncHolder(View itemView) {
            super(itemView);
            mncImage= (ImageView) itemView.findViewById(R.id.mncImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                 Toast.makeText(mContext, "you selected "+getPosition(),
                               Toast.LENGTH_SHORT).show();
                    }
            });


        }
    }
}
