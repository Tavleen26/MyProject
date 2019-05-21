package com.tavleen.myproject.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tavleen.myproject.R;
import com.tavleen.myproject.adapter.BabyAdapter;
import com.tavleen.myproject.adapter.VaccineAdapter;
import com.tavleen.myproject.listener.OnRecyclerItemClickListener;
import com.tavleen.myproject.model.Baby;
import com.tavleen.myproject.model.Vaccination;

import java.util.ArrayList;
import java.util.List;

public class VaccinationChartActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    ArrayList<Baby> babies;
    ArrayList<Vaccination> vaccineList;
    Boolean updateMode;

    VaccineAdapter vaccineAdapter;


   String id;
    BabyAdapter babyAdapter;

    Baby baby;
    int position;

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    void initViews(){

        recyclerView=findViewById(R.id.recyclerView);

        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        firebaseUser=auth.getCurrentUser();
        Intent rcv=getIntent();
          id=getIntent().getStringExtra("keyBabyId");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_babies);
        initViews();

        fetchVaccineFromCloudDB();
    }



    void fetchVaccineFromCloudDB(){

        db.collection("user").document(firebaseUser.getUid()).collection("baby").document(id).get().addOnCompleteListener(this, new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isComplete()) {

                    Baby baby = task.getResult().toObject(Baby.class);


                        vaccineAdapter = new VaccineAdapter(VaccinationChartActivity.this, R.layout.list_item, baby.vaccinations);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VaccinationChartActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(vaccineAdapter);
                        recyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        });
    }




}
