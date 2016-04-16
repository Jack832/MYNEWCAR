package com.example.projectheena.mynewcar.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectheena.mynewcar.R;

import java.util.List;

/**
 * Created by projectheena on 4/4/16.
 */
public class CarTypesAdapter extends RecyclerView.Adapter<CarTypesAdapter.CarTypeHolder> {
    LayoutInflater mInflator;
    List<CarList> mCarTypes;

    public CarTypesAdapter(Context context, List<CarList> carTypes) {
        this.mCarTypes=carTypes;
        mInflator=LayoutInflater.from(context);

    }

    @Override
    public CarTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflator.inflate(R.layout.cartypecustomrow,parent,false);
        CarTypeHolder carTypeHolder= new CarTypeHolder(view);
        return carTypeHolder;
    }

    @Override
    public void onBindViewHolder(CarTypeHolder holder, int position) {
        Log.d("MyMNC", "position car type: " + position);
        final CarList carList=mCarTypes.get(position);
        holder.carName.setText(carList.mTypeName);
        holder.carIcon.setImageResource(carList.mTypeIcon);
    }

    @Override
    public int getItemCount()
    {
        return mCarTypes.size();
    }

    public class CarTypeHolder extends RecyclerView.ViewHolder{
        ImageView carIcon;
        TextView  carName;
        public CarTypeHolder(View itemView)
        {
            super(itemView);
            carIcon= (ImageView) itemView.findViewById(R.id.carIcon);
            carName= (TextView) itemView.findViewById(R.id.carType);
        }
    }
}
