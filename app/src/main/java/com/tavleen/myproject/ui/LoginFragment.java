package com.tavleen.myproject.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.tavleen.myproject.R;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EditText eTxtPhone, eTxtOtp;
    Button btnProceed;

    PhoneAuthProvider authProvider;
    FirebaseAuth auth;




    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_login, container, false);
        eTxtPhone=view.findViewById(R.id.editTextPhone);
        eTxtOtp=view.findViewById(R.id.editTextOtp);
        btnProceed=view.findViewById(R.id.buttonProceed);
        btnProceed.setOnClickListener(clickListener);

        authProvider=PhoneAuthProvider.getInstance();
        auth=FirebaseAuth.getInstance();
        return view;
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone=eTxtPhone.getText().toString().trim();

//            authProvider.verifyPhoneNumber(phone,70, TimeUnit.SECONDS,LoginFragment.this,callbacks);
               authProvider.verifyPhoneNumber(phone,70,TimeUnit.SECONDS,getActivity(),callbacks);


        }

    };


    public void goToAttract()
    {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            signInUser(phoneAuthCredential);
        }


        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getActivity(),"Verification Failed"+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };
    void signInUser(PhoneAuthCredential phoneAuthCredential){
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isComplete()){

                    FirebaseUser user=task.getResult().getUser();
                    String userId=user.getUid();
                     goToAttract();

                }
            }
        });
    }

}
