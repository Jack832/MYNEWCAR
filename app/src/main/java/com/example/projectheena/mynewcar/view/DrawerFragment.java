package com.example.projectheena.mynewcar.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectheena.mynewcar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public static  final String PREFNAME="Test";
    public static final String KEYVALUE="UserKnowAboutDrawer";
    boolean mUserLearned;
    boolean mFromSaved;
    DrawerLayout mDrawerLayout;
    View  mContentView;
    ActionBarDrawerToggle mActionBarTo;
    DrawerListAdapter mAdapter;



    public DrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mUserLearned=Boolean.valueOf(readfromPref(getActivity(),KEYVALUE,"false"));
        if(savedInstanceState !=null){
            mFromSaved=true;
        }

    }

    public static String readfromPref(Context context, String prefName, String prefValue)
    {
        SharedPreferences shared=context.getSharedPreferences(PREFNAME,context.MODE_PRIVATE);
        return shared.getString(prefName,prefValue);
    }

    private void saveToPreference(Context context, String prefName, String prefValue)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences
                (PREFNAME,context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        editor.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_drawer, container, true);
        mRecyclerView= (RecyclerView) layout.findViewById(R.id.navigationList);
        mAdapter= new DrawerListAdapter(getActivity(),getDrawerList());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        return layout;
    }

    // data required for list
    public static List<CarList> getDrawerList()
    {
        List<CarList> data=new ArrayList<>();
        String name[]={"HOME","HOT DEALS","COMPARE CARS","SEARCH CARS","BRANDS",
                       "BOOK A TEST DRIVE","CAR LEASE","UPCOMING CARS","NEWS/BLOG",
                       "ABOUT US","CONTACT US","WHY US","SETTING","LOGIN"};
        int    icon[]={R.drawable.prictag,R.drawable.message,R.drawable.compares,
                       R.drawable.search,R.drawable.prictag,R.drawable.cars,
                       R.drawable.cars, R.drawable.cars, R.drawable.cars,
                       R.drawable.news,R.drawable.aboutus,R.drawable.contactus,
                        R.drawable.whyus,R.drawable.setting,R.drawable.login};
        for(int i=0;i<name.length;i++)
        {
            CarList carList= new CarList();
            carList.mNavIcon=icon[i];
            carList.mNavTitle=name[i];
            data.add(carList);
        }
        return data;
    }


    //set the values for Navigation Drawer
    public void setValues(int navigationDrawer, DrawerLayout layout,
                                                            Toolbar toolbar)
    {
        mContentView=getActivity().findViewById(navigationDrawer);
        mDrawerLayout=layout;
        mActionBarTo=new ActionBarDrawerToggle(getActivity(),layout,toolbar,
                                              R.string.open,R.string.close){

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                if(!mUserLearned)
                {
                    mUserLearned=true;
                    //saveToPreference(getActivity(),KEYVALUE,mUserLearned+"");

                }
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if(!mUserLearned && !mFromSaved){
            mDrawerLayout.closeDrawer(mContentView);
        }
        mDrawerLayout.addDrawerListener(mActionBarTo);
        mDrawerLayout.post(new Runnable()
        {
            @Override
            public void run() {
                mActionBarTo.syncState();
            }
        });


    }


}
