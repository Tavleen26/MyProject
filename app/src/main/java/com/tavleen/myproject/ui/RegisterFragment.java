package com.tavleen.myproject.ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tavleen.myproject.R;
import com.tavleen.myproject.model.User;
import com.tavleen.myproject.model.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    EditText eTxtName,eTxtEmail,eTxtPassword,eTxtPhone;
    Button btnRegister;

    TextView txtLogin;

    User user;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    FirebaseFirestore db;
    FirebaseUser firebaseUser;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        eTxtName=view.findViewById(R.id.editTextName);
        eTxtEmail=view.findViewById(R.id.editTextEmail);
        eTxtPassword=view.findViewById(R.id.editTextPassword);
        eTxtPhone=view.findViewById(R.id.editTextPhone);
        btnRegister=view.findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(this);


        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);


        user=new User();

        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.buttonRegister){
            user.name=eTxtName.getText().toString();
            user.email=eTxtEmail.getText().toString();
            user.password=eTxtPassword.getText().toString();
            user.phone=eTxtPhone.getText().toString();

            if(Util.isInternetConnected(getActivity())) {
                registerUser();
            }
            else{
                Toast.makeText(getActivity(),"Please connect to Internet and Try Again",Toast.LENGTH_LONG).show();
            }
        }else {

            Intent intent=new Intent(getActivity(), LoginFragment.class);
            startActivity(intent);
        }
    }

    void registerUser(){
        progressDialog.show();

        auth.createUserWithEmailAndPassword(user.email,user.password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if(task.isComplete()){
                    saveUserInCloud();
                }
            }
        });


    }
    void saveUserInCloud(){
        firebaseUser=auth.getCurrentUser();
        db.collection("user").document(firebaseUser.getUid()).set(user).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(),user.name +"Registered Successfully",Toast.LENGTH_LONG).show();
                progressDialog.dismiss();


                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }
}
