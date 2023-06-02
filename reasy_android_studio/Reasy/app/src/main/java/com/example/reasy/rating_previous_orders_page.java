package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class rating_previous_orders_page extends AppCompatActivity {
    private int id,sid,oid,ok=0,f;
    private int founds = 0;
    private double sev,a;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> clist=new ArrayList<>();
    private ArrayList<Integer> orders=new ArrayList<>();
    private ArrayList<user> ul=new ArrayList<user>();
    private ArrayList<customer> cl=new ArrayList<>();
    private ArrayList<product_order> po=new ArrayList<>();
    private ArrayList<Integer> sl=new ArrayList<>(),ol=new ArrayList<>(),oh=new ArrayList<>();

    private LinearLayout linearLayout;
    private RatingBar ratingBar;
    private EditText cs;
    private TextView t7,p,b;
    private ArrayList<order> foundo=new ArrayList<>();

    public void rateMenu(View v){
        a = (double) ratingBar.getRating();
        if(f==0){
            ok=0;
            rating.createRating(rating_previous_orders_page.this, id, sid, a, sev, oid, ok);
            popupMessage();
        }
        else if(cs.getText().toString().compareTo("")==0){
            ok=0;
            rating.createRating(rating_previous_orders_page.this, id, sid, a, sev, oid, ok);
            popupMessage();
        }
        else {
            oid = Integer.valueOf(cs.getText().toString());
            if (ol.contains(oid)) {
                menu.changeMenuRating(rating_previous_orders_page.this, sid, a);
                t7.setText(" ");
                oh = customer.getRatedOrders(rating_previous_orders_page.this, id, oid);
                ok = 1;
                rating.createRating(rating_previous_orders_page.this, id, sid, a, sev, oid, ok);
                popupMessage();
            } else {
                t7.setText("Not valid order id choice.");
            }
        }
    }

    public void popupMessage() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your rating!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(rating_previous_orders_page.this,shop_list_page.class);
                Bundle b = new Bundle();
                //Add your data to bundle
                b.putInt("id", id);
                b.putInt("sid", sid);

                intent.putExtras(b);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_previous_orders_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sl=bundle.getIntegerArrayList("sl");
        ol=bundle.getIntegerArrayList("ol");
        sid= bundle.getInt("sid");
        sev= bundle.getDouble("sev");

        linearLayout = findViewById(R.id.linear_layout);
        ratingBar = findViewById(R.id.ratingBar3);
        cs = findViewById(R.id.orderid);
        t7=findViewById(R.id.name8);

        cl=customer.getCustomer(rating_previous_orders_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                p = findViewById(R.id.textView43);
                p.setText(String.valueOf(c.getPoints())+"pts");
                b = findViewById(R.id.textView44);
                b.setText(String.valueOf(user.getBalance(rating_previous_orders_page.this,id))+"\u20AC");
            }
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);

        slist=shop.getShops(rating_previous_orders_page.this);

        foundo=order.getOrder(rating_previous_orders_page.this,id,sid);
        if(foundo.isEmpty()){
            orders.add(0);
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
            tv.setTypeface(typeface);

            f=0;
            lp.setMargins(20, 0, 20, 30);
            tv.setLayoutParams(lp);

            linearLayout.addView(tv);
        }
        else{
            for(order o: foundo)
            {
                TextView tv = new TextView(this);
                tv.setText("");
                tv.setTextSize(18);
                tv.setHeight(192);
                tv.setWidth(966);
                tv.setPadding(30, 90, 30, 90);
                tv.setId(o.getOrder_id());
                tv.setGravity(Gravity.CENTER);
                tv.setBackgroundResource(R.drawable.menu_item);
                tv.setTypeface(typeface);
                tv.setLayoutParams(lp);

                tv.setMovementMethod(new ScrollingMovementMethod());
                po=product_order.getOrderD(rating_previous_orders_page.this,o.getOrder_id());
                tv.setText("Order id "+o.getOrder_id()+"\n");
                for(product_order p:po) {
                    tv.setText(tv.getText().toString()+p.getName() +"\n");

                }
                linearLayout.addView(tv);
            }
        }
    }

    public void goBack(View v){
        Intent intent=new Intent(this,rating_form_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putIntegerArrayList("sl",sl);
        intent.putExtras(b);
        startActivity(intent);
    }
}