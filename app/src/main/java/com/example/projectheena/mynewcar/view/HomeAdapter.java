package com.example.projectheena.mynewcar.view;

import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectheena.mynewcar.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by projectheena on 2/4/16.
 * purpose
 * 1.Adapter to set the Home Screen.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CarHolder>
{
    LayoutInflater inflater;
    List<CarList> carlistDetails= Collections.emptyList();
    Context mContext;
    public HomeAdapter(Context context,List<CarList> carlist)
    {
        mContext=context;
        inflater= LayoutInflater.from(context);
        carlistDetails=carlist;
    }

    @Override
    public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.carinfo_row,parent,false);
        CarHolder carHolder= new CarHolder(view);
        return carHolder;
    }

    @Override
    public void onBindViewHolder(CarHolder holder, int position) {
        Log.d("MyMNC", "position car Details: " + position);
        final CarList data=carlistDetails.get(position);
        holder.carName.setText(data.mCarName);
        holder.carPrice.setText(data.mCarPrice);
        holder.saving.setText(data.mCarSaving);
        holder.valid.setText(data.mCarValid);
        holder.carImage.setImageResource(data.mCarIcon);

    }

    @Override
    public int getItemCount() {
        return carlistDetails.size();
    }

    public class  CarHolder extends  RecyclerView.ViewHolder
    {
        ImageView carImage;
        TextView  carName,carPrice,saving,valid;
        public CarHolder(View itemView)
        {
            super(itemView);
            carImage= (ImageView) itemView.findViewById(R.id.carIcon);
            carName=(TextView)itemView.findViewById(R.id.carName);
            carPrice=(TextView)itemView.findViewById(R.id.exShowroom);
            saving=(TextView)itemView.findViewById(R.id.totalSaving);
            valid=(TextView)itemView.findViewById(R.id.validUntil);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "you selected " + getPosition(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
