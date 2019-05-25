package com.tavleen.myproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tavleen.myproject.R;
import com.tavleen.myproject.model.User;

public class PhoneRegisterActivity extends AppCompatActivity {

    EditText eTxtPhone, eTxtOtp;
    Button btnProceed;

    PhoneAuthProvider authProvider;
    FirebaseAuth auth;
    FirebaseInstanceId firebaseInstanceId;
    User user;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    void initViews(){
        eTxtPhone=findViewById(R.id.editTextPhone);
        eTxtOtp=findViewById(R.id.editTextOTP);
        btnProceed=findViewById(R.id.buttonProceed);
        btnProceed.setOnClickListener(clickListener);
        user=new User();


        authProvider=PhoneAuthProvider.getInstance();
        auth=FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_register);
        initViews();


    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone=eTxtPhone.getText().toString().trim();

            //Toast.makeText(PhoneRegisterActivity.this,"Authentication failed, check your phone number or sign up",Toast.LENGTH_LONG).show();

//            authProvider.verifyPhoneNumber(phone,70, TimeUnit.SECONDS,PhoneRegisterActivity.this,callbacks);
            if(firebaseUser!=null) {
//                authProvider.verifyPhoneNumber(phone,70, TimeUnit.SECONDS,PhoneRegisterActivity.this,callbacks);
                Intent intent = new Intent(PhoneRegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(PhoneRegisterActivity.this,ProfileActivity.class);
                startActivity(intent);
            }

            authProvider.verifyPhoneNumber(phone,70, TimeUnit.SECONDS,PhoneRegisterActivity.this,callbacks);

        }
    };

    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            signInUser(phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(PhoneRegisterActivity.this,"Verification Failed"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

    void signInUser(PhoneAuthCredential phoneAuthCredential){
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){


//                    if(firebaseUser!=null) {
//                        Intent intent = new Intent(PhoneRegisterActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//                    else{
//                        Intent intent = new Intent(PhoneRegisterActivity.this,ProfileActivity.class);
//                        startActivity(intent);
//                    }

                }
            }
        });
    }



}


