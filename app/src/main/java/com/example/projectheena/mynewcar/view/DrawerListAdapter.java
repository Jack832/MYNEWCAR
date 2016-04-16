package com.example.projectheena.mynewcar.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectheena.mynewcar.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by projectheena on 4/4/16.
 *
 * purpose:
 * 1. to inflate the customLayout
 */
public class DrawerListAdapter extends RecyclerView.Adapter<DrawerListAdapter.DrawerListHolder>
{
    LayoutInflater mInflater;
    Context context;
    List<CarList> mDrawerList= Collections.emptyList();
    public DrawerListAdapter(Context context, List<CarList> list)
    {
        this.context=context;
        mInflater=LayoutInflater.from(context);
        this.mDrawerList=list;

    }
    @Override
    public DrawerListHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=mInflater.inflate(R.layout.navigationcustomrow,parent,false);
        DrawerListHolder listHolder= new DrawerListHolder(view);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(DrawerListHolder holder, int position)
    {
        final CarList carList=mDrawerList.get(position);
        holder.menuName.setText(carList.mNavTitle);
        holder.userIcon.setImageResource(carList.mNavIcon);
    }

    @Override
    public int getItemCount() {
        return mDrawerList.size();
    }

    public class DrawerListHolder extends RecyclerView.ViewHolder
    {
        ImageView userIcon;
        TextView  menuName;
        public DrawerListHolder(View itemView)
        {
            super(itemView);
            userIcon= (ImageView) itemView.findViewById(R.id.navIcon);
            menuName= (TextView) itemView.findViewById(R.id.iconName);
        }
    }
}
