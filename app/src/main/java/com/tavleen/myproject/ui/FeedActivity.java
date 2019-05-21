package com.tavleen.myproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.tavleen.myproject.R;

public class FeedActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn4months, btn4to6, btn6to9, btn9to12, btn12to18, btn18to24, btn24months;

    void initViews(){
        btn4months=findViewById(R.id.button4months);
        btn4to6=findViewById(R.id.button4to6);
        btn6to9=findViewById(R.id.button6to9);
        btn9to12=findViewById(R.id.button9to12);
        btn12to18=findViewById(R.id.button12to18);
        btn18to24=findViewById(R.id.button18to24);
        btn24months=findViewById(R.id.button24);

        btn4months.setOnClickListener(this);
        btn4to6.setOnClickListener(this);
        btn6to9.setOnClickListener(this);
        btn9to12.setOnClickListener(this);
        btn12to18.setOnClickListener(this);
        btn18to24.setOnClickListener(this);
        btn24months.setOnClickListener(this);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4months:
                Intent intent=new Intent(FeedActivity.this,FeedDetailsActivity.class);
                intent.putExtra("keyMonths",1);
                startActivity(intent);
                break;

            case R.id.button4to6:
                Intent intent1=new Intent(FeedActivity.this,FeedDetailsActivity.class);
                intent1.putExtra("keyMonths",2);
                startActivity(intent1);
                break;

            case R.id.button6to9:
                Intent intent2=new Intent(FeedActivity.this,FeedDetailsActivity.class);
                intent2.putExtra("keyMonths",3);
                startActivity(intent2);
                break;

            case R.id.button9to12:
                Intent intent3=new Intent(FeedActivity.this,FeedDetailsActivity.class);
                intent3.putExtra("keyMonths",4);
                startActivity(intent3);
                break;

            case R.id.button12to18:
                Intent intent4=new Intent(FeedActivity.this,FeedDetailsActivity.class);
                intent4.putExtra("keyMonths",5);
                startActivity(intent4);
                break;

            case R.id.button18to24:
                Intent intent5=new Intent(FeedActivity.this,FeedDetailsActivity.class);
                intent5.putExtra("keyMonths",6);
                startActivity(intent5);
                break;

            case R.id.button24:
                Intent intent6=new Intent(FeedActivity.this,FeedDetailsActivity.class);
                intent6.putExtra("keyMonths",7);
                startActivity(intent6);
                break;
        }


    }
}
