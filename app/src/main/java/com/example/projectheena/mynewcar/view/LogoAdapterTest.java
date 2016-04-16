package com.example.projectheena.mynewcar.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectheena.mynewcar.R;

import java.util.List;

/**
 * Created by projectheena on 5/4/16.
 */
public class LogoAdapterTest extends RecyclerView.Adapter<LogoAdapterTest.LogoTest> {

    LayoutInflater mInflater;
    List<CarList> mCarLogos;
    Context mLogoContext;
    public LogoAdapterTest(Context context, List<CarList> carLists) {
        this.mCarLogos=carLists;
        this.mLogoContext=context;
        mInflater= LayoutInflater.from(context);
    }

    @Override
    public LogoTest onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.logocustomrow,parent,false);
        LogoTest logoHolder= new LogoTest(view);
        return logoHolder;

    }

    @Override
    public void onBindViewHolder(LogoTest holder, final int position) {
        final CarList carList=mCarLogos.get(position);
        holder.logo.setImageResource(carList.mLogoIcon);
        holder.logoName.setText(carList.mLogoName);

    }

    @Override
    public int getItemCount() {
        return mCarLogos.size();
    }
    public class LogoTest extends RecyclerView.ViewHolder
    {
        ImageView logo;
        TextView logoName;
        public LogoTest(View itemView)
        {
            super(itemView);
            logo= (ImageView) itemView.findViewById(R.id.logo);
            logoName= (TextView) itemView.findViewById(R.id.logoName);
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mLogoContext,"you selected "
                            +getPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
