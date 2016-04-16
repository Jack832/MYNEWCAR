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
public class LogoAdapter extends RecyclerView.Adapter<LogoAdapter.LogoHolder> {
    public RecyclerView modelLogo;
    LayoutInflater mInflater;
    List<CarList> mCarLogos;
    boolean isSelected = true;
    Context mContext;

    public LogoAdapter(Context context, List<CarList> carLists, RecyclerView model) {
        this.mCarLogos = carLists;
        Log.d("MyMNC","list size"+mCarLogos.size());
        mContext = context;
        mInflater = LayoutInflater.from(context);
        modelLogo = model;
    }

    @Override
    public LogoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("MyMNC", "onCreateViewHolder: ");
        View view = mInflater.inflate(R.layout.logocustomrow, parent, false);
        Log.d("MyMNC", "onCreateViewHolder1: ");
        LogoHolder logoHolder = new LogoHolder(view);
        Log.d("MyMNC", "onCreateViewHolde2r: ");
        return logoHolder;
    }

    @Override
    public void onBindViewHolder(final LogoHolder holder, final int position) {
        Log.d("MyMNC", "position: "+position);
        final CarList carList = mCarLogos.get(position);
        holder.logo.setImageResource(carList.mLogoIcon);
        holder.logoName.setText(carList.mLogoName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MyMNC", "count" + modelLogo.getChildCount());
                Log.e("MyMNC", "position " + position);
                for (int i = 0; i < modelLogo.getChildCount(); i++) {
                    try {
                        modelLogo.getChildAt(i).setBackgroundResource(0);
                        Log.d("MyMNC","child position"+modelLogo.getChildAt(i).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    modelLogo.getChildAt(position).
                            setBackgroundResource(R.drawable.border);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("MyMNC", "mCarLogos.size(): "+mCarLogos.size());
        return mCarLogos.size();
    }

    public class LogoHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView logoName;

        public LogoHolder(final View itemView) {
            super(itemView);
            Log.d("MyMNC", "LogoHolder: ");
            logo = (ImageView) itemView.findViewById(R.id.logo);
            logoName = (TextView) itemView.findViewById(R.id.logoName);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "you selected " + getPosition(),
//                            Toast.LENGTH_SHORT).show();
//                    Log.e("position", "position" +modellogo.getChildCount());
//
//                    for(int i=0;i<modellogo.getChildCount();i++)
//                    {
//                        modellogo.getChildAt(i).setVisibility(View.INVISIBLE);
//                    }
//                   // itemView.setBackgroundResource(R.drawable.border);
//                }
//            });
        }
    }
}
