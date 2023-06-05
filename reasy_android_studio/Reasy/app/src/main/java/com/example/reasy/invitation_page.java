package com.example.reasy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class invitation_page extends AppCompatActivity {

    private int id,rid,a,num,fnum,w;
    private String date,r;
    private ArrayList<Integer> recid=new ArrayList<>(),fid=new ArrayList<>(),invited=new ArrayList<>();
    private ArrayList<Integer> av=new ArrayList<>();
    private ArrayList<reception> rec=new ArrayList<>();
    private ArrayList<String> recd=new ArrayList<>(),cn=new ArrayList<>();
    private ArrayList<customer> cl=new ArrayList<>();
    private EditText e;
    private TextView po,b;

    public void fillAndValidate(View view){
        r=String.valueOf(e.getText());
        invitation.createInv(invitation_page.this,av,rid,date);
        popupMessage();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_page);
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
        w=bundle.getInt("w");
        e = findViewById(R.id.details2);
        cl=customer.getCustomer(invitation_page.this);
        for(customer c: cl) {
            if (c.getId() == id) {
                po = findViewById(R.id.textView61);
                po.setText(String.valueOf(c.getPoints()) + "pts");
                b = findViewById(R.id.textView65);
                b.setText(String.valueOf(user.getBalance(invitation_page.this, id)) + "\u20AC");
            }
        }
    }

    public void popupMessage(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You have successfully made your invitation!");
        alertDialogBuilder.setNegativeButton("ok", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(invitation_page.this,main_page.class);
                Bundle b = new Bundle();
                //Add your data to bundle
                b.putInt("id", id);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void goBack(View v){
        if(w==0) {
            Intent intent = new Intent(this, change_people_number_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putIntegerArrayList("recid", recid);
            b.putStringArrayList("recd", recd);
            b.putInt("rid", rid);
            b.putIntegerArrayList("fid", fid);
            b.putString("date", date);
            b.putIntegerArrayList("av",av);
            b.putInt("num",num);
            b.putInt("fnum",fnum);

            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, friend_list_page.class);
            Bundle b = new Bundle();
            //Add your data to bundle
            b.putInt("id", id);
            b.putIntegerArrayList("recid", recid);
            b.putStringArrayList("recd", recd);
            b.putInt("rid", rid);
            b.putIntegerArrayList("fid", fid);
            b.putString("date", date);

            intent.putExtras(b);
            startActivity(intent);
        }
    }
}