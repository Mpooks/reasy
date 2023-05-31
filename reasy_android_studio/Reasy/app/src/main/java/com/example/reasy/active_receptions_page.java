package com.example.reasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class active_receptions_page extends AppCompatActivity {
    private int id;
    private ArrayList<Integer> recid=new ArrayList<>(),fid=new ArrayList<>();
    private ArrayList<reception> rec=new ArrayList<>();
    private ArrayList<String> recd=new ArrayList<>();
    private LinearLayout linearLayout;
    private TextView text4;

    public void invite(int i){
        fid=customer.getFriendList(active_receptions_page.this,id);
        if(fid.isEmpty()){
            popupMessage();
        }else{
            show(i);
        }
    }

    public void show(int i){
        Intent intent=new Intent(this,friend_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putIntegerArrayList("fid",fid);
        b.putIntegerArrayList("recid",recid);
        b.putStringArrayList("recd",recd);
        b.putInt("rid",i);
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_receptions_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        recid= bundle.getIntegerArrayList("recid");
        recd= bundle.getStringArrayList("recd");
        linearLayout = findViewById(R.id.linear_layout);
        text4=findViewById(R.id.textViewc16);
        if(recid.isEmpty()){
            TextView tv = new TextView(this);
            text4.setText("");
            tv.setText("You have already created invitations for all your receptions.");
            tv.setTextSize(18);
            tv.setHeight(192);
            tv.setWidth(966);
            tv.setPadding(30, 90, 30, 90);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundResource(R.drawable.pt);
            tv.setTextColor(Color.parseColor("#000000"));
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            tv.setTypeface(typeface);


            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            linearLayout.addView(tv);
        }
        else{
            for (int i : recid) {
                TextView tv = new TextView(this);
                tv.setText("Reception id: " + i + "\nDate: " + recd.get(recid.indexOf(i)));
                tv.setTextSize(18);
                tv.setHeight(192);
                tv.setWidth(966);
                tv.setPadding(30, 90, 30, 90);
                tv.setId(i);
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.menu_item);
                Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
                tv.setTypeface(typeface);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        invite(i);
                    }
                });

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20, 0, 20, 30);
                tv.setLayoutParams(lp);

                linearLayout.addView(tv);
            }
        }
    }
    public void goBack(View v){
        Intent intent=new Intent(this,main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You don't have people in your friend list!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(active_receptions_page.this,main_page.class);
                Bundle b = new Bundle();
                b.putInt("id",id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
