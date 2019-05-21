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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tavleen.myproject.R;
import com.tavleen.myproject.model.User;
import com.tavleen.myproject.model.Util;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    EditText eTxtName, eTxtEmail;
    Button btnProfile;




    User users;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    FirebaseFirestore db;
    FirebaseUser firebaseUser;
    FirebaseInstanceId firebaseInstanceId;

    //
    void initViews() {
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
        btnProfile = findViewById(R.id.buttonProfile);
        btnProfile.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        users = new User();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        firebaseInstanceId = FirebaseInstanceId.getInstance();






    }
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
    }

    @Override
    public void onClick(View v) {
        users.name=eTxtName.getText().toString();
        users.email=eTxtEmail.getText().toString();

          getToken();



    }


    void clearFields() {
        eTxtName.setText("");
        eTxtEmail.setText("");
    }

    void getToken(){
        firebaseInstanceId.getInstanceId()
                .addOnCompleteListener(this, new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isComplete()){
                            users.token = task.getResult().getToken();
                            saveUser();

                           clearFields();

                            Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
                            startActivity(intent);

                        }
                    }
                });
    }

    void saveUser() {
        firebaseUser = auth.getCurrentUser();
        db.collection("user").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(users).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
}
}