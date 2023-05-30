package com.example.reasy;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Calendar;


public class organize_reception_page extends AppCompatActivity {

    private int id,g,found=0,changed=0,foundp=0,av=0;
    private ArrayList<Integer> oh=new ArrayList<>();
    private ArrayList<String> ar_n=new ArrayList<>();
    private ArrayList<reception_area> ra=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private CalendarView calendar;
    private TextView text5,p,b;
    private EditText per;
    private String dateb,n,d;

    public void chooseDateAndPeople(View v){
        per = findViewById(R.id.editTextNumber2);
        if(per.getText().toString().length()==0){
            foundp=1;
            text5.setText("Invalid format.");
        }
        else if(Integer.valueOf(per.getText().toString())<=0){
            foundp=1;
            text5.setText("Invalid choice.");
        }
        else{
            text5.setText("");
            foundp=0;
        }
        if(changed==0) {
            dateb= String.valueOf(java.time.LocalDate.now());
        }
        if(foundp==0){
            g=Integer.valueOf(per.getText().toString());
            DatabaseManager dbm = new DatabaseManager(organize_reception_page.this);
            try {
                dbm.open();
                Cursor cursor=dbm.fetchRA();
                if (cursor.moveToFirst()) {
                    do {
                        ra.add(new reception_area(cursor.getString(1),cursor.getInt(0),cursor.getInt(3),cursor.getDouble(2)));
                    } while (cursor.moveToNext());
                }
                cursor.close();
                dbm.close();
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
            for(reception_area r: ra){
                    av=r.getAvailability(organize_reception_page.this,g,dateb,r.getReception_area_id(),r.getNum_of_guests());
                    if(av==1){
                        oh.add(r.getReception_area_id());
                        ar_n.add(r.getName());
                    }
            }
            if(oh.isEmpty()){
                text5.setText("There are no available reception areas for your choice.");
            }
            else{
                show(v);
            }
        }
    }
    public void show(View v) {
        Intent intent=new Intent(this, reception_area_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putInt("g",g);
        b.putString("date",dateb);
        b.putIntegerArrayList("ar_r",oh);
        b.putStringArrayList("ar_n",ar_n);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize_reception_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        cl=customer.getCustomer(organize_reception_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                p = findViewById(R.id.textView);
                p.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView2);
                b.setText(String.valueOf(user.getBalance(organize_reception_page.this,id))+"\u20AC");
            }
        }
        calendar = (CalendarView)findViewById(R.id.calendar3);
        text5 = (TextView)findViewById(R.id.warning2);
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
            }
        });
    }

    public void goBack(View v){
        Intent intent=new Intent(this, main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }


}