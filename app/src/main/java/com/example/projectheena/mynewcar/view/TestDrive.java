package com.example.projectheena.mynewcar.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.projectheena.mynewcar.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by projectheena on 5/4/16.
 *
 * purpose:
 * 1. this shows recyclerview,
 * 2.date selection
 * 3.details
 * 4.submit using post
 */
public class TestDrive extends AppCompatActivity
{
    RecyclerView mTestDrive;
    RecyclerView.Adapter mDriveAdapter;
    ImageButton mDateButton;
    Spinner mSpinner;
    Toolbar mToolBar;
    DatePickerDialog mDatePickerDialog;
    SimpleDateFormat mSimpleDate;
    Button mSubmit;
    int day;
    int month;
    int year;
    EditText mDate,mName,mEmail,mphone,mLocation;
    String date,name,email,phone,location,fuelType;
    BufferedReader bufferedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testdrive);
        mToolBar= (Toolbar) findViewById(R.id.testDrive);
        setSupportActionBar(mToolBar);
        mToolBar.setTitle("Test Drive");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTestDrive= (RecyclerView) findViewById(R.id.driveTest);
        LinearLayoutManager testDriveLayout= new LinearLayoutManager(this,
                                        LinearLayoutManager.HORIZONTAL,false);
        mTestDrive.setLayoutManager(testDriveLayout);
        mDriveAdapter= new LogoAdapterTest(this,logoDetails());
        mTestDrive.setAdapter(mDriveAdapter);

        mSpinner = (Spinner) findViewById(R.id.selectfuel);
        String types[]=getResources().getStringArray(R.array.fuelType);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                                 android.R.layout.simple_spinner_item,types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        //spinner with toast
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                                        int position, long id)
            {
                fuelType=parent.getItemAtPosition(position).toString();
                Toast.makeText(TestDrive.this, "You Selected"+fuelType,
                                                    Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //date picker
        mSimpleDate= new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        mDateButton= (ImageButton) findViewById(R.id.datePicker);
        mDate= (EditText) findViewById(R.id.editTextDate);
        Calendar calendar=Calendar.getInstance();
        day=calendar.DAY_OF_MONTH;
        month=calendar.MONTH;
        year=calendar.YEAR;
        mDateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mDatePickerDialog = new DatePickerDialog(TestDrive.this,new
                                             DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                                 int monthOfYear, int dayOfMonth)
                    {
                       mDate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                },year,month,day);
                mDatePickerDialog.show();
            }
        });

        mName= (EditText) findViewById(R.id.name);
        mphone= (EditText) findViewById(R.id.phone);
        mEmail= (EditText) findViewById(R.id.email);
        mLocation= (EditText) findViewById(R.id.location);
        mSubmit= (Button) findViewById(R.id.submit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SubmitProcess post= new SubmitProcess();
                post.execute();
            }
        });
    }

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

    public void postdata()
    {
        date=mDate.getText().toString();
        name=mName.getText().toString();
        phone=mphone.getText().toString();
        email=mEmail.getText().toString();
        location=mLocation.getText().toString();
        Log.w("Data", "postdata: "+date+name+phone+email+location);

        String data="";
        String text;
        String line;
        try
        {
            data=URLEncoder.encode("enq_full_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
            data+= "&"+URLEncoder.encode("enq_email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8");
            data+= "&"+URLEncoder.encode("enq_phone","UTF-8")+"="+URLEncoder.encode(phone, "UTF-8");
            data+= "&"+URLEncoder.encode("city","UTF-8")+"="+URLEncoder.
                                                                           encode(location, "UTF-8");
            data+= "&"+URLEncoder.encode("added_date","UTF-8")+"="+URLEncoder.encode(date, "UTF-8");
            data+= "&"+URLEncoder.encode("fuel_type","UTF-8")+"="+URLEncoder.encode(fuelType, "UTF-8");
            data+= "&"+URLEncoder.encode("model_name","UTF-8")+"="+URLEncoder.encode("Honda", "UTF-8");
            data+= "&"+URLEncoder.encode("brand_name","UTF-8")+"="+URLEncoder.encode(fuelType, "UTF-8");
            Log.w("Data", "Alldata: "+data);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        try
        {
            URL url= new URL("http://mncbeta.com/common/feedback_enquiry");
            URLConnection connection= (URLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStreamWriter wr= new OutputStreamWriter
                                                (connection.getOutputStream());
            Log.w("server", "server hit:");
            wr.write(data);
            wr.flush();
            wr.close();
            bufferedReader = new BufferedReader(new InputStreamReader
                        (connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                if ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line);
                }
                text = stringBuilder.toString();
                bufferedReader.close();
                Log.w("data", "received:" + text);

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //Async Task for submit Data
    public class SubmitProcess extends AsyncTask<Void,Void,Void>{
       ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog= new ProgressDialog(TestDrive.this);
            progressDialog.setTitle("Downloading");
            progressDialog.setMessage("Postion data");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            postdata();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

        }
    }
}
