package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class calendar_page extends AppCompatActivity {
    private int id,sid,cap,val,found=0,changed=0;
    private ArrayList<String> oh;
    private LinearLayout linearLayout;
    private ArrayList<shop> slist=new ArrayList<>();
    private main_lists ml;
    private CalendarView calendar;
    private TextView date_view;
    private TextView text3,text4;
    private String dateb,n,d;
    public void chooseDate(){
        ml= main_lists.createLists();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        Bundle bundle = getIntent().getExtras();
        oh= bundle.getStringArrayList("open");
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");
        for(shop s: slist){
            if(s.getId()==sid){
                cap=s.getCapacity();
                n=s.getName();
            }
        }
        linearLayout = findViewById(R.id.linear_layout);
        text3 = findViewById(R.id.sname);
        text3.setText(n);
        text4 = findViewById(R.id.textViewc);
        text4.setText("Choose a date of your reservation:");
        calendar = (CalendarView)findViewById(R.id.calendar);
        date_view = (TextView)findViewById(R.id.warning);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                changed=1;
                String m=String.valueOf(month+1);
                String dom=String.valueOf(dayOfMonth);
                if(month<9){
                    m='0'+String.valueOf(month+1);
                }
                if(dayOfMonth<10){
                    dom='0'+String.valueOf(dayOfMonth);
                }
                dateb=year + "-"+ m + "-" + dom;
                Date d1 = (new GregorianCalendar(year, month, dayOfMonth)).getTime();
                d= (String) android.text.format.DateFormat.format("EEEE", d1);
                switch(d){
                    case "Sunday":val=0;
                    break;
                    case "Monday":val=1;
                    break;
                    case "Tuesday":val=2;
                    break;
                    case "Wednesday":val=3;
                    break;
                    case "Thursday":val=4;
                    break;
                    case "Friday":val=5;
                    break;
                    case "Saturday":val=6;
                    break;
                }
                if((oh.get(val).compareTo("closed"))==0){
                    found=1;
                }
                else{
                    found=0;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);
        chooseDate();
    }
    public void show(View v){
        if(changed==0) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            if((oh.get(day).compareTo("closed"))==0){
                found=1;
            }
            else{
                dateb= String.valueOf(java.time.LocalDate.now());
                found=0;
            }
        }
        if(found==0) {
            Intent intent = new Intent(this, customers_time_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putInt("sid", sid);
            b.putInt("cap", cap);
            b.putString("date", dateb);
            b.putStringArrayList("open", oh);
            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            date_view.setText("This shop is closed for the chosen date");
        }
    }
    public void goBack(View v){
        Intent intent=new Intent(this,chosen_shop_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putInt("sid",sid);
        intent.putExtras(b);
        startActivity(intent);
    }
}