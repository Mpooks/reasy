package com.example.reasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class shop_list_page extends AppCompatActivity {
    private int id,sid;
    private main_lists ml;
    private ArrayList<user> ul=new ArrayList<>();
    private ArrayList<reservation> arrayList;
    private LinearLayout linearLayout;
    private ArrayList<reservation> rl=new ArrayList<reservation>();
    private ArrayList<shop> slist=new ArrayList<>();
    private String sname;

    public void showPrevReservations(){
        linearLayout = findViewById(R.id.linear_layout);

        for(reservation r: rl)
        {
            Button b = new Button(this);

            b.setText("Reservation id: "+r.getReservation_id()+"\nShop: "+sname);
            b.setTextSize(18);
            b.setHeight(192);
            b.setWidth(966);
            b.setPadding(30, 90, 30, 90);
            b.setId(r.getReservation_id());
            b.setGravity(Gravity.CENTER);
            b.setBackgroundResource(R.drawable.menu_item);
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            b.setTypeface(typeface);
            b.setAllCaps(false);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            b.setLayoutParams(lp);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(shop_list_page.this,rating_form_page.class);
                    sid=b.getId();
                    //Create the bundle
                    Bundle b = new Bundle();
                    //Add your data to bundle
                    b.putInt("sid",sid);
                    b.putInt("id",id);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            });
            linearLayout.addView(b);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list_page);
        showPrevReservations();
    }

    public void goBack(View v){
        Intent intent=new Intent(this,main_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}

