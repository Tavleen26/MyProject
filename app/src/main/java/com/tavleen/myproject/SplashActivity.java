package com.tavleen.myproject;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.tavleen.myproject.ui.HomeActivity;
import com.tavleen.myproject.ui.LoginViewActivity;
import com.tavleen.myproject.ui.MainActivity;
import com.tavleen.myproject.ui.RegistrationActivity;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.sendEmptyMessageDelayed(101,3000);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        getSupportActionBar().hide();
        if(user==null){
            handler.sendEmptyMessageDelayed(101, 3000);
        }else{
            handler.sendEmptyMessageDelayed(201, 3000);
        }


    }

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==101){
                Intent intent=new Intent(SplashActivity.this, LoginViewActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };


}
