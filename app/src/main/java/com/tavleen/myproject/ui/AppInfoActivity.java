package com.tavleen.myproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tavleen.myproject.R;

public class AppInfoActivity extends AppCompatActivity {

    TextView txtTitle, txtInfo;

    void initViews(){
        txtTitle=findViewById(R.id.textViewAppTitle);
        txtInfo=findViewById(R.id.textViewInfo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        initViews();
    }
}
