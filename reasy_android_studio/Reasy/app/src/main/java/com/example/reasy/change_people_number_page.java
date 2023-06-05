package com.example.reasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class change_people_number_page extends AppCompatActivity {

    private int id,rid,a,num,fnum,newnum;
    private String date;
    private ArrayList<Integer> recid=new ArrayList<>(),fid=new ArrayList<>(),invited=new ArrayList<>();
    private ArrayList<Integer> av=new ArrayList<>();
    private ArrayList<reception> rec=new ArrayList<>();
    private ArrayList<String> recd=new ArrayList<>(),cn=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private LinearLayout linearLayout;
    private TextView text4,po,b,t;
    private EditText v;
    public void changeNumber(View view){
        newnum=Integer.valueOf(v.getText().toString());
        if(newnum<=0){
            t.setText("Invalid choice.");
        }
        if(newnum>fnum){
            t.setText("The new number of invited guests must be less than the previous one.");
        }
        else{
            t.setText("");
            reception.updateRecN(change_people_number_page.this,rid,newnum,fnum);
            show();
        }
    }
    public void show(){
        Intent intent = new Intent(this, invitation_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id", id);
        b.putIntegerArrayList("fid", fid);
        b.putIntegerArrayList("recid", recid);
        b.putStringArrayList("recd", recd);
        b.putInt("rid", rid);
        b.putString("date", date);
        b.putIntegerArrayList("av",av);
        b.putInt("num",num);
        b.putInt("fnum",fnum);
        b.putInt("w",0);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_people_number_page);
        Bundle bundle = getIntent().getExtras();
        id= bundle.getInt("id");
        rid= bundle.getInt("rid");
        recid= bundle.getIntegerArrayList("recid");
        recd= bundle.getStringArrayList("recd");
        fid= bundle.getIntegerArrayList("fid");
        date=bundle.getString("date");
        av=bundle.getIntegerArrayList("av");
        num=bundle.getInt("num");
        fnum=bundle.getInt("fnum");

        linearLayout = findViewById(R.id.linear_layout);
        text4=findViewById(R.id.textViewc22);
        t=findViewById(R.id.textViewc19);
        v=findViewById(R.id.editTextNumber3);
        cl=customer.getCustomer(change_people_number_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                po = findViewById(R.id.textView56);
                po.setText(String.valueOf(c.getPoints()) + "pts");
                b = findViewById(R.id.textView57);
                b.setText(String.valueOf(user.getBalance(change_people_number_page.this, id)) + "\u20AC");
            }
        }
        text4.setText(String.valueOf(fnum));
    }
    public void goBack(View v){
        Intent intent=new Intent(this,friend_list_page.class);
        Bundle b = new Bundle();
        //Add your data to bundle
        b.putInt("id",id);
        b.putIntegerArrayList("recid",recid);
        b.putStringArrayList("recd",recd);
        b.putInt("rid",rid);
        b.putIntegerArrayList("fid",fid);
        b.putString("date",date);

        intent.putExtras(b);
        startActivity(intent);
    }
}