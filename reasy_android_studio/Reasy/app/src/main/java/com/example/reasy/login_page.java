package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class login_page extends AppCompatActivity {

    private TextView t;
    private main_lists ml;
    EditText emailt,passt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ml= main_lists.createLists();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }
    public void GCMain(View v) {
        int found=0;
        emailt=(EditText)findViewById(R.id.email);
        String email=emailt.getText().toString();
        passt=(EditText)findViewById(R.id.pass);
        String pass=passt.getText().toString();
        ArrayList<user> ul = new ArrayList<user>();
        ul=ml.getUser_list();
        for(int i=0;i<ul.size();i++){
            user u=ul.get(i);
            String j=u.getEmail();
            String p= u.getPassword();
            if((j.compareTo(email)==0)&&(p.compareTo(pass)==0)){
                found=1;
            }
        }
        if(found==1) {
            Intent intent = new Intent(this, final_order_page.class);
            startActivity(intent);
        }
        else{
            t = findViewById(R.id.logt);
            t.setText("Wrong credentials");
        }
    }
    public void goBack(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}