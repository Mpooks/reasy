package com.example.reasy;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class friend_list_page extends AppCompatActivity {
        private int id,rid,a,num,fnum;
        private String date;
        private ArrayList<Integer> recid=new ArrayList<>(),fid=new ArrayList<>(),invited=new ArrayList<>();
        private ArrayList<Integer> av=new ArrayList<>();
        private ArrayList<reception> rec=new ArrayList<>();
        private ArrayList<String> recd=new ArrayList<>(),cn=new ArrayList<>();
        private LinearLayout linearLayout;
        private TextView text4;

        public void chooseFriends(View view){
            av=customer.getAv(friend_list_page.this,invited,date);
            fnum=invited.size();
            if(av.size()==invited.size()){
                a=0;
                num=invited.size()-av.size();
            }
            else{
                a=1;
                num=invited.size();
            }
            show();
        }

        public void show(){
            if(a==0) {
                Intent intent = new Intent(this, invitation_page.class);
                Bundle b = new Bundle();
                //Add your data to bundle
                b.putInt("id", id);
                b.putIntegerArrayList("fid", fid);
                b.putIntegerArrayList("recid", recid);
                b.putStringArrayList("recd", recd);
                b.putInt("rid", rid);
                b.putString("date", date);
                b.putIntegerArrayList("av",av);
                b.putInt("num",num);
                b.putInt("fnum",fnum);
                intent.putExtras(b);
                b.putInt("w",1);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, change_people_number_page.class);
                Bundle b = new Bundle();
                //Add your data to bundle
                b.putInt("id", id);
                b.putIntegerArrayList("fid", fid);
                b.putIntegerArrayList("recid", recid);
                b.putStringArrayList("recd", recd);
                b.putInt("rid", rid);
                b.putString("date", date);
                b.putIntegerArrayList("av",av);
                b.putInt("num",num);
                b.putInt("fnum",fnum);
                b.putInt("w",0);
                intent.putExtras(b);
                startActivity(intent);
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_friend_list_page);
            Bundle bundle = getIntent().getExtras();
            id= bundle.getInt("id");
            rid= bundle.getInt("rid");
            recid= bundle.getIntegerArrayList("recid");
            recd= bundle.getStringArrayList("recd");
            fid= bundle.getIntegerArrayList("fid");
            date=bundle.getString("date");
            linearLayout = findViewById(R.id.linear_layout);
            text4=findViewById(R.id.textViewc18);
            DatabaseManager dbm = new DatabaseManager(friend_list_page.this);
            try {
                dbm.open();
                for(int i:fid){
                    Cursor cursor=dbm.fetchCN(i);
                    cn.add(cursor.getString(0));
                }
                dbm.close();
            } catch (SQLDataException e) {
                throw new RuntimeException(e);
            }
            for (int i : fid) {
                CheckBox tv = new CheckBox(this);
                Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
                tv.setTextSize(18);
                tv.setHeight(192);
                tv.setWidth(966);
                tv.setPadding(30, 90, 30, 90);
                tv.setId(i);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.pt);
                tv.setText("Friend id: " + i + "\nName: " + cn.get(fid.indexOf(i)));
                tv.setTextColor(Color.parseColor("#000000"));
                tv.setTypeface(typeface);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        invited.add(i);
                    }
                });

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 0, 20, 30);
                tv.setLayoutParams(lp);

                linearLayout.addView(tv);
            }
        }
        public void goBack(View v){
            Intent intent=new Intent(this,active_receptions_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id",id);
            b.putIntegerArrayList("recid",recid);
            b.putStringArrayList("recd",recd);
            intent.putExtras(b);
            startActivity(intent);
        }
    }