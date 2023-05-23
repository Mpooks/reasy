package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class login_page extends AppCompatActivity {

    private TextView t;
    private int idu;
    private main_lists ml;
    private ArrayList<user> ulist=new ArrayList<>();
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> clist=new ArrayList<>();
    EditText emailt,passt;
    DatabaseManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }
    public void GCMain(View v) {
        ml= main_lists.createLists();
        int found=0;
        String a=null;
        emailt=(EditText)findViewById(R.id.email);
        String email=emailt.getText().toString();
        passt=(EditText)findViewById(R.id.pass);
        String pass=passt.getText().toString();
        //ulist = (ArrayList<user>) ml.getUser_list().clone();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        clist = (ArrayList<customer>) ml.getCustomer_list().clone();
        t = findViewById(R.id.logt);
        ulist=user.getUsers(login_page.this);
        for(user u: ulist){
            if(((u.getEmail()).compareTo(email)==0)&&((u.getPassword()).compareTo(pass)==0)){
                idu=u.getId();
                found=1;
            }
        }

        if(found==1) {
            for(customer c: clist){
                if((c.getEmail()).compareTo(email)==0){
                    Intent intent = new Intent(this, main_page.class);
                    Bundle b = new Bundle();
                    //Add your data to bundle
                    b.putInt("id", idu);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
            for(shop s: slist){
                if((s.getEmail()).compareTo(email)==0){
                    Intent intent = new Intent(this, shop_main_page.class);
                    Bundle b = new Bundle();
                    //Add your data to bundle
                    b.putInt("id", idu);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        }
        else{
            t.setText("Wrong credentials");
        }
    }
    public void goBack(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}