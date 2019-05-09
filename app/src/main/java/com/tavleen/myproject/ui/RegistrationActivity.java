package com.tavleen.myproject.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tavleen.myproject.R;
import com.tavleen.myproject.model.User;
import com.tavleen.myproject.model.Util;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eTxtName,eTxtEmail,eTxtPassword,eTxtPhone;
    Button btnRegister;

    TextView  txtLogin;

    User user;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    void initViews(){
        eTxtName=findViewById(R.id.editTextName);
        eTxtEmail=findViewById(R.id.editTextEmail);
        eTxtPassword=findViewById(R.id.editTextPassword);
        eTxtPhone=findViewById(R.id.editTextPhone);
        txtLogin=findViewById(R.id.textViewLogin);
        btnRegister=findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        user=new User();

        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
    }

    @Override
    public void onClick(View v) {

        // Get the data from UI and put it into User Object
//        user.name=eTxtName.getText().toString();
//        user.email=eTxtEmail.getText().toString();
//        user.password=eTxtPassword.getText().toString();

        int id=v.getId();
        if(id==R.id.buttonRegister){
            user.name=eTxtName.getText().toString();
            user.email=eTxtEmail.getText().toString();
            user.password=eTxtPassword.getText().toString();
            user.phone=eTxtPhone.getText().toString();

            if(Util.isInternetConnected(this)) {
                registerUser();
            }
            else{
                Toast.makeText(this,"Please connect to Internet and Try Again",Toast.LENGTH_LONG).show();
            }
        }else {
//

            Intent intent=new Intent(RegistrationActivity.this, PhoneRegisterActivity.class);
            startActivity(intent);
        }
    }

    void registerUser(){
        progressDialog.show();

        auth.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if(task.isComplete()){
//                    Toast.makeText(RegistrationActivity.this,user.name+"Registered Successfully",Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//
//                    Intent intent=new Intent(RegistrationActivity.this, HomeActivity.class);
//                    startActivity(intent);
                    saveUserInCloud();
                }
            }
        });


    }


    void saveUserInCloud(){

//        db.collection("users").add(user).addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentReference> task) {
//                if(task.isComplete()){
//                    Toast.makeText(RegistrationActivity.this,user.name+"Registered Successfully",Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//
//                    Intent intent=new Intent(RegistrationActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                   finish();
//                }
//            }
//        });


        firebaseUser=auth.getCurrentUser();
        db.collection("users").document(firebaseUser.getUid()).set(user).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegistrationActivity.this,user.name +"Registered Successfully",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

                Intent intent=new Intent(RegistrationActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
