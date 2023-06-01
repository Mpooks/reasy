package com.example.reasy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class rating_form_page extends AppCompatActivity {

    private int id,sid,numofr,cn;
    private int rated = 0;
    private double rate;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> clist=new ArrayList<>();
    private ArrayList<rating> ratingh;
    private ArrayList<String> oh;
    private TextView text3,text4,text5,text6,text7,text8;
    private ArrayList<Integer> sl=new ArrayList<>();
    private LinearLayout linearLayout;
    private menu m;
    private ArrayList<product_menu> arrayList;
    private customer cu;
    private String n,a,p;
    private EditText cs;
    RatingBar ratingBar;
    Button br;
    public rating_form_page() {

    }
   public void chooseShopAndFillRating(){

    }
    public void show(View v){
        if(rated == 0){
            text7.setText("You have to rate our shop to continue.");
        }
        if(rated == 1){
            Intent intent=new Intent(this,rating_previous_orders_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id",id);
            b.putInt("sid",sid);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sl=bundle.getIntegerArrayList("sl");
        linearLayout = findViewById(R.id.linear_layout);


        br=findViewById(R.id.rate2);
        ratingBar = findViewById(R.id.ratingBar);
        cs = findViewById(R.id.shopidt);
        sid=Integer.parseInt(cs.getText().toString());


        br.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double a = (double)ratingBar.getRating();
                shop.changeRating(rating_form_page.this,sid,a);
                /*text6.setText(rate+ " ("+numofr+")");
                if(ratingh.isEmpty()==true){
                    cu.addPointsRShop(50);
                    text7.setText("You got 50 points!");
                    rated = 1;
                }
                if(ratingh.isEmpty()==false){
                    text7.setText("You got 0 points!");
                    rated = 1;
                }*/
            }

        });
    }

    public void goBack(View v){
        Intent intent=new Intent(this,shop_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        intent.putExtras(b);
        startActivity(intent);
    }
}