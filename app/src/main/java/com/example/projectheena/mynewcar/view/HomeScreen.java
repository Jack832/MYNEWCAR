package com.example.projectheena.mynewcar.view;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectheena.mynewcar.MainActivity;
import com.example.projectheena.mynewcar.R;
import com.example.projectheena.mynewcar.controller.DataHandler;
import com.squareup.picasso.Picasso;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by projectheena on 2/4/16.
 *
 * purpose:
 * 1.home screen contains toolbar,recyclerview,slider
 * spinner
 *
 */

public class HomeScreen extends AppCompatActivity
{
    private Toolbar toolbar;
    private Spinner spinner;
    ViewPager viewPager;
    RecyclerView recyclerView,recyclerimages,modelLogo,modelTypes;
    RecyclerView.Adapter adapter,adapterForImages,logoAdapter,carTypesAdapter;
    TextView searchBar;
    RelativeLayout mSearch;
    boolean isTextView=true;
    RangeSeekBar mrangeSeekBar;
    DataHandler mDataHandler;
//    public List<String> mImages;
    PagerAdapter pagerAdapter;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        mDataHandler= new DataHandler();
//        mImages= new ArrayList<>();
        //rangebar
        mrangeSeekBar= (RangeSeekBar) findViewById(R.id.rangeBar);
        mrangeSeekBar.setRangeValues(0, 100);

        SpinnerAdapter spinnerAdapter= ArrayAdapter.createFromResource
                (getApplicationContext(),R.array.locations,R.layout.simple_spinner);
        Spinner spinnerNavigation=new Spinner(getSupportActionBar().
                                                              getThemedContext());
        spinnerNavigation.setAdapter(spinnerAdapter);
        toolbar.addView(spinnerNavigation, 0);
        spinnerNavigation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location =parent.getItemAtPosition(position).toString();
                Toast.makeText(HomeScreen.this, "You Selected "+location, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerHorizontal);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new HomeAdapter(this,carDetails());
        recyclerView.setAdapter(adapter);

        recyclerimages= (RecyclerView) findViewById(R.id.recyclerImages);
        LinearLayoutManager layoutManagerImages= new LinearLayoutManager
                (this,LinearLayoutManager.VERTICAL,false);
        recyclerimages.setLayoutManager(layoutManagerImages);
        adapterForImages= new ImagesAdapter(this,carImages());
        recyclerimages.setAdapter(adapterForImages);

        viewPager= (ViewPager) findViewById(R.id.imageViewPager);
        LoadImagesAsync loadImagesAsync= new LoadImagesAsync();
        loadImagesAsync.execute();
        pagerAdapter= new ImageSlider(HomeScreen.this);
        pagerAdapter.notifyDataSetChanged();


        //calling navigation drawer
        DrawerFragment drawerFragment= (DrawerFragment) getSupportFragmentManager()
                                          .findFragmentById(R.id.navigationDrawer);
        drawerFragment.setValues(R.id.navigationDrawer, (DrawerLayout)
                                           findViewById(R.id.Navigation), toolbar);
        //search menu on click
        LinearLayoutManager searchlogo=new LinearLayoutManager(this,
                                             LinearLayoutManager.HORIZONTAL,false);
        modelLogo= (RecyclerView) findViewById(R.id.searchHorizontal);
        modelLogo.setLayoutManager(searchlogo);
        Log.d("myMNC", "length: "+this.logoDetails().size());
        logoAdapter= new LogoAdapter(this,logoDetails(),modelLogo);
        modelLogo.setAdapter(logoAdapter);


        LinearLayoutManager  carTypes=new LinearLayoutManager(this,
                                         LinearLayoutManager.HORIZONTAL,false);
        modelTypes= (RecyclerView) findViewById(R.id.searchVertical);
        modelTypes.setLayoutManager(carTypes);
        carTypesAdapter= new CarTypesAdapter(this,getCarTypes());
        modelTypes.setAdapter(carTypesAdapter);
        searchBar= (TextView) findViewById(R.id.searchBar);

//        mSearch= (RelativeLayout) findViewById(R.id.selectId);
//        mSearch.setVisibility(View.GONE);
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //visible and invisible
                if(isTextView)
                {
                    findViewById(R.id.searchMenu).setVisibility(View.GONE);
                    findViewById(R.id.searchMenu).setEnabled(false);
                    isTextView=false;
                }
                else
                {
                    Animation up= AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.slide_up);
                    findViewById(R.id.searchMenu).startAnimation(up);
                    findViewById(R.id.searchMenu).setVisibility(View.VISIBLE);
                    isTextView=true;
                }
            }
        });
    }

    // data for car types
    private List<CarList> getCarTypes()
    {
        List<CarList> data = new ArrayList<>();
        int icon[] = {R.drawable.sedan, R.drawable.station, R.drawable.convertible
                      ,R.drawable.coupe};
        String title[] = {"sedan","hatchback","convertible","coupe"};
        for(int i=0;i<icon.length;i++)
        {
            CarList carList= new CarList();
            carList.mTypeIcon=icon[i];
            carList.mTypeName=title[i];
            data.add(carList);
        }
        return data;
    }

    //data for car logo
    public static List<CarList> logoDetails()
    {
        List<CarList> data= new ArrayList<>();
        int icon[] = {R.drawable.chervolet, R.drawable.honda, R.drawable.dastan
                     ,R.drawable.ford};
        String title[] = {"chervolet","honda","dastan","ford"};
        for(int i=0;i<icon.length;i++){
            CarList carList= new CarList();
            carList.mLogoIcon=icon[i];
            carList.mLogoName=title[i];
            data.add(carList);
        }

       return data;
    }

    //populating dummy data for Mnc Images
    public List<CarList> carImages()
    {
        List<CarList> images= new ArrayList<>();
        int icons[]={R.drawable.mncone,R.drawable.mnctwo,R.drawable.mncthree,R.drawable.mncthree};
        for(int i=0;i<icons.length;i++)
        {
            CarList current = new CarList();
            current.mCarIcon=icons[i];
            images.add(current);
        }
        return images;
    }

    //populating dummy data for horizontal list
    public static List<CarList> carDetails()
    {
        List<CarList> data = new ArrayList<>();
        int icon[] = {R.drawable.carone, R.drawable.cartwo, R.drawable.carthree,R.drawable.carthree};
        String title[] = {"Maruti-Suzuki DZire LXi [O]",
                          "Maruti-Suzuki Wagon R VXi [O] AT",
                          "Maruti-Suzuki Ciaz VXI+ AT","Maruti-Suzuki Ciaz VXI+ AT"};
        String price[] ={"548,123","452,123","325,123","325,123"};
        String validateTill[]={"3 March","31 March","13 March","13 March"};
        String saving[]={"2,583","1,483","3,583","3,583"};

        for (int i = 0; i < icon.length && i < title.length; i++)
        {
            CarList current = new CarList();
            current.mCarIcon = icon[i];
            current.mCarName = title[i];
            current.mCarPrice= price[i];
            current.mCarValid=validateTill[i];
            current.mCarSaving=saving[i];
            data.add(current);
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem=menu.findItem(R.id.searchicon);
        SearchManager searchManager=(SearchManager) HomeScreen.this.
                                       getSystemService(Context.SEARCH_SERVICE);
        return super.onCreateOptionsMenu(menu);
    }

    //To load Images for Slider
    public class LoadImagesAsync extends AsyncTask<Void,Void,Void> {
        ProgressDialog mProgressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog= new ProgressDialog(HomeScreen.this);
            mProgressDialog.setTitle("Downloading");
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mDataHandler.getJsonData();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressDialog.dismiss();
            viewPager.setAdapter(pagerAdapter);

        }
    }
}
