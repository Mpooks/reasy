package com.example.reasy;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class rating_previous_orders_page extends AppCompatActivity {
    private int id,sid,oid;
    private int founds = 0;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> clist=new ArrayList<>();
    private ArrayList<Integer> orders=new ArrayList<>();
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<customer> cl=new ArrayList<>();
    private LinearLayout linearLayout;
    private main_lists ml;
    private ArrayList<order> oh,foundo=new ArrayList<>();
    /*public rating_previous_orders_page() {
    }

    public void rateMenu(){
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sid= bundle.getInt("sid");

        linearLayout = findViewById(R.id.linear_layout);

       // ml= main_lists.createLists();
        ul = (ArrayList<user>) ml.getUser_list().clone();
        slist = (ArrayList<shop>) ml.getShop_list().clone();
        cl = (ArrayList<customer>) ml.getCustomer_list().clone();
        for(customer c: cl){
            if(c.getId()==id){
                oh=c.getOrderHistory();
                if(!(c.getOrderHistory()==null))
                {
                    for (order p : oh)
                    {
                        if(p.getShop_id()==sid)
                        {
                            orders.add(p.getOrder_id());
                            founds=1;
                        }
                    }
                    if(founds==0)
                    {
                        orders.add(0);
                    }
                }
                else
                {
                    orders.add(0);
                }
            }
        }

        if(orders.contains(0))
        {
            TextView tv = new TextView(this);
            tv.setText("You have no previous orders to rate.");
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
            for(customer c: cl)
            {
                if(c.getId()==id)
                {
                    oh=c.getOrderHistory();
                }
            }
            for(int i: orders)
            {
                for(order o:oh)
                {
                    if(o.getOrder_id()==i)
                    {
                        foundo.add(o);
                    }
                }
            }
            TextView b = new TextView(this);
            b.setText("Select one of your previous orders to rate: ");
            b.setTextSize(18);
            b.setHeight(192);
            b.setWidth(966);
            b.setPadding(30, 90, 30, 90);
            b.setGravity(Gravity.CENTER);
            b.setBackgroundResource(R.drawable.pt);
            b.setTextColor(Color.parseColor("#000000"));
            Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);
            b.setTypeface(typeface);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(20, 0, 20, 30);
            b.setLayoutParams(lp);

            linearLayout.addView(b);
            for(order o: foundo)
            {
                TextView tv = new TextView(this);
                tv.setText("");
                for(product_order po: o.getProducts())
                {
                    tv.setText(String.valueOf(tv.getText())+po.getName()+"\n\n");
                }

                tv.setTextSize(18);
                tv.setHeight(250);
                tv.setWidth(966);
                tv.setPadding(30, 90, 30, 90);
                tv.setId(o.getOrder_id());
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.menu_item);
                tv.setTypeface(typeface);
                tv.setLayoutParams(lp);

                linearLayout.addView(tv);
                RatingBar r = new RatingBar(this);
                linearLayout.addView(r);
                r.setNumStars(5);

                r.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));



            }

        }}



    public void validate(){

    }
    public void createFinalRating(){

    }
    public void show(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_previous_orders_page);
        rateMenu();
    }

    public void goBack(View v){
        Intent intent=new Intent(this,rating_form_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putInt("sid",sid);
        intent.putExtras(b);
        startActivity(intent);
    }*/
}