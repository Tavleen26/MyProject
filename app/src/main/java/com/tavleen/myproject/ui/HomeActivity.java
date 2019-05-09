package com.tavleen.myproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.tavleen.myproject.R;
import com.tavleen.myproject.SplashActivity;

public class HomeActivity extends AppCompatActivity   {

    TextView txtResult;
//    Button btnAddBaby,btnVaccine;
    TextView btnAddBaby,btnVaccine;
    SharedPreferences sharedPreferences;

    FirebaseAuth auth;


    void initViews() {
        btnAddBaby = findViewById(R.id.buttonAddBaby);
        btnVaccine=findViewById(R.id.buttonVaccine);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);

        btnAddBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);
                btnAddBaby.setAnimation(myAnim);

                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Intent intent = new Intent(HomeActivity.this, AddBaby.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }

        });

        btnVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(myAnim);
                btnVaccine.setAnimation(myAnim);
                myAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Intent intent = new Intent(HomeActivity.this,VaccinationKnowledgeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        initViews();

//        txtResult=findViewById(R.id.textViewResult);
//        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
//        txtResult.setText("Hello "+sharedPreferences.getString("keyName",null));
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,0,"LogOut");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==101){
            auth.signOut();

            Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
