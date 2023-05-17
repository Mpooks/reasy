package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login_page extends AppCompatActivity {

    private Button loginButton,backb;
    EditText emailt,passt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }
    public void GCMain(View v) {
        emailt=(EditText)findViewById(R.id.email);
        passt=(EditText)findViewById(R.id.pass);
        Intent intent=new Intent(this,final_order_page.class);
        startActivity(intent);
    }
}