package com.example.reasy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class reception_area_page extends AppCompatActivity {

    private Button btn1,bckbtn;
    EditText tv1,tv2,tv3;

     public void chooseReceptionArea(View v)
     {
         tv1 = (EditText)findViewById(R.id.t1);
         tv2 = (EditText)findViewById(R.id.t2);
         tv3 = (EditText)findViewById(R.id.t3);


         tv3.setText("LALAL " + reception_area.getAvailability(30,40));;
     }


    public void show(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_area_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //back btn in ActionBar

    }
}