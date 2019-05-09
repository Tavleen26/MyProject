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
import com.tavleen.myproject.listener.OnRecyclerItemClickListener;
import com.tavleen.myproject.model.Baby;

import java.util.ArrayList;
import java.util.List;

public class AllBabiesActivity extends AppCompatActivity implements OnRecyclerItemClickListener {

    RecyclerView recyclerView;
    ArrayList<Baby> babies;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_babies);
        initViews();

        fetchBabyFromCloudDB();
    }

    void fetchBabyFromCloudDB(){
        db.collection("user").document(firebaseUser.getUid()).collection("baby").get().addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isComplete()){

                   babies=new ArrayList<>();

                    QuerySnapshot querySnapshot=task.getResult();
                    List<DocumentSnapshot> documentSnapshots=querySnapshot.getDocuments();
                    for(DocumentSnapshot snapshot:documentSnapshots){
                        String docId=snapshot.getId();
                        Baby baby=snapshot.toObject(Baby.class);
                        baby.docId=docId;
                        babies.add(baby);

                        getSupportActionBar().setTitle("Total babies:"+babies.size());

                        babyAdapter=new BabyAdapter(AllBabiesActivity.this,R.layout.babies_list_item,babies);

                        babyAdapter.setOnRecyclerItemClickListener(AllBabiesActivity.this);

                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(AllBabiesActivity.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(babyAdapter);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }


                }else{
                    Toast.makeText(AllBabiesActivity.this,"Some Error",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    void  deleteBabyFromCloudDb(){
        db.collection("user").document(firebaseUser.getUid()).collection("baby").document(baby.docId).delete().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()){
                    Toast.makeText(AllBabiesActivity.this,"Deletion Finished",Toast.LENGTH_LONG).show();
                    babies.remove(position);
                    babyAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(AllBabiesActivity.this,"Deletion Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    void  showBabyDetails(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(baby.name+"Details:");
//        builder.setMessage(customer.toString());

        builder.setMessage(baby.toString());
        builder.setPositiveButton("Done",null);
        AlertDialog dialog=builder.create();
        dialog.show();



    }


    void askForDeletion(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete"+baby.name);
        builder.setMessage("Are You Sure ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            deleteBabyFromCloudDb();
            }
        });
        builder.setNegativeButton("Cancel",null);
        AlertDialog dialog=builder.create();
        dialog.show();

    }


    void showOptions(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        String [] items={"View "+baby.name,"Update "+baby.name,"Delete "+baby.name,"Cancel "+baby.name,"Vaccination Chart "+baby.name};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                          showBabyDetails();
                        break;
                    case 1:
                        Intent intent=new Intent(AllBabiesActivity.this,AddBaby.class);
                        intent.putExtra("keyBaby",baby);
                        startActivity(intent);
                        break;
                    case 2:
                        askForDeletion();
                        break;
                    case 3:
                        break;
                    case 4:
                        Intent intent1=new Intent(AllBabiesActivity.this,VaccinationChartActivity.class);
                        intent1.putExtra("keyBabyId",baby.docId);
                        startActivity(intent1);
                        break;
                }
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void onItemClick(int position) {
        this.position=position;
        baby=babies.get(position);

        showOptions();
    }
}
