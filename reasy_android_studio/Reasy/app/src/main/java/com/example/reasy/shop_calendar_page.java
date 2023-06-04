package com.example.reasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class shop_calendar_page extends AppCompatActivity {
    private int id,val,found=0,changed=0;
    private ArrayList<Integer> t1=new ArrayList<>();
    private ArrayList<table> tl=new ArrayList<>();
    private String dateb,d;
    private LinearLayout linearLayout;
    private ArrayList<shop> sl=new ArrayList<>();
    private TextView date_view,b;
    private CalendarView calendar;

    public void chooseDate(){
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sl=shop.getShops(shop_calendar_page.this);
        for(shop c: sl) {
            if (c.getId() == id) {
                b = findViewById(R.id.tb);
                b.setText(String.valueOf(user.getBalance(shop_calendar_page.this,id))+"\u20AC");
            }
        }
        linearLayout = findViewById(R.id.linear_layout);
        calendar = (CalendarView)findViewById(R.id.calendar2);
        date_view = (TextView)findViewById(R.id.warning11);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                t1.clear();
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
                tl=shop.getOpeningHoursAndTables(shop_calendar_page.this,id,val);
                if(tl.isEmpty()){
                    t1.add(0);
                }
                else {
                    for (table t : tl) {
                        t1.add(t.getTable_id());
                    }
                }
            }
        });
    }

    public void show(View v){
        if(changed==0) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            dateb=String.valueOf(java.time.LocalDate.now());
            tl=shop.getOpeningHoursAndTables(shop_calendar_page.this,id,day-1);
            if(tl.isEmpty()){
                t1.add(0);
            }
            else {
                for (table t : tl) {
                    t1.add(t.getTable_id());
                }
            }
        }
            Intent intent = new Intent(this, table_list_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putString("date", dateb);
            b.putIntegerArrayList("tables",t1);
            intent.putExtras(b);
            startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_calendar_page);
        chooseDate();
    }

    public void onClick(View view) {
        Intent intent1 = new Intent(shop_calendar_page.this, table_list_page.class);
        startActivity(intent1);
    }

    public void goBack(View v){
        Intent intent=new Intent(this,shop_main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}