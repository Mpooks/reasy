package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AAMainActivity extends AppCompatActivity {

    private Button btn1,nextpage1;
    private EditText tv1,tv2,tv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aamain);

        nextpage1 = findViewById(R.id.nextpage1);
        nextpage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNextActivity();
            }
        });

        }

    public void openNextActivity(){
        Intent intent = new Intent(this, reception_area_page.class);
        startActivity(intent);

    }

   /* public void add(View v){

        tv1 = (EditText)findViewById(R.id.tv1);
        tv2 = (EditText)findViewById(R.id.tv2);
        tv3 = (EditText)findViewById(R.id.tv3);

        int n1 = Integer.parseInt(tv1.getText().toString());
        int n2 = Integer.parseInt(tv2.getText().toString());

        if(n1>n2){
            tv3.setText("n1>n2\n");
        }
        else if (n1<n2) {
            tv3.setText("n1<n2\n");
        }
        else {
            tv3.setText("n1=n2\n");
        }

    }

    */
}