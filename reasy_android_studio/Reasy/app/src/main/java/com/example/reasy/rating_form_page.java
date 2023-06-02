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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class rating_form_page extends AppCompatActivity {

    private int id,sid;
    private ArrayList<shop> slist=new ArrayList<>();
    private ArrayList<customer> clist=new ArrayList<>();
    private ArrayList<Integer> sl=new ArrayList<>(),ol=new ArrayList<>();
    private LinearLayout linearLayout;
    private ArrayList<order> oh;

    private EditText cs;
    private TextView t7;
    RatingBar ratingBar;
    Button br;
    public rating_form_page() {

    }
   public void chooseShopAndFillRating(View v){
       sid=Integer.valueOf(cs.getText().toString());
       if(sl.contains(sid)) {
           double a = (double) ratingBar.getRating();
           shop.changeRating(rating_form_page.this, sid, a);
           t7.setText(" ");
           oh=customer.getOrderAndRatingHistory(rating_form_page.this,id,sid);
           for (order r:oh){
               ol.add(r.getOrder_id());
           }
       }
       else{
           t7.setText("Not valid shop id choice.");
       }
    }
    public void show(View v){
            Intent intent=new Intent(this,rating_previous_orders_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id",id);
            b.putIntegerArrayList("sl",sl);
            b.putIntegerArrayList("ol",ol);
            intent.putExtras(b);
            startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_form_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        sl=bundle.getIntegerArrayList("sl");
        linearLayout = findViewById(R.id.linear_layout);
        ratingBar = findViewById(R.id.ratingBar);
        cs = findViewById(R.id.shopidt);
        t7=findViewById(R.id.name7);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Typeface typeface = getResources().getFont(R.font.seoulhangang_cbl_regular);

        slist=shop.getShops(rating_form_page.this);

        for(int i:sl) {
            for (shop s : slist) {
                if (i == s.getId()) {
                    TextView tv = new TextView(this);
                    tv.setText(i+". "+s.getName());
                    tv.setTextSize(18);
                    tv.setHeight(192);
                    tv.setWidth(966);
                    tv.setPadding(30, 90, 30, 90);
                    tv.setId(s.getId());
                    tv.setGravity(Gravity.CENTER);
                    tv.setBackgroundResource(R.drawable.menu_item);
                    tv.setTypeface(typeface);


                    lp.setMargins(20, 0, 20, 30);
                    tv.setLayoutParams(lp);

                    linearLayout.addView(tv);
                }
            }
        }

    }

    public void goBack(View v){
        Intent intent=new Intent(this,shop_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putIntegerArrayList("sl",sl);
        intent.putExtras(b);
        startActivity(intent);
    }
}