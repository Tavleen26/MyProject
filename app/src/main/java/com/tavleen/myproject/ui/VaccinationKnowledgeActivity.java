package com.tavleen.myproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tavleen.myproject.R;
import com.tavleen.myproject.adapter.VaccineAdapter;
import com.tavleen.myproject.adapter.VaccineDetailsAdapter;
import com.tavleen.myproject.listener.MyListener;
import com.tavleen.myproject.model.Baby;
import com.tavleen.myproject.model.Vaccination;
import com.tavleen.myproject.model.VaccinationDetails;

import java.util.ArrayList;


public class VaccinationKnowledgeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{


    ArrayList<VaccinationDetails> vaccineList;
    ListView listView;
    VaccineAdapter vaccineAdapter;
    VaccineDetailsAdapter vaccineDetailsAdapter;
    MyListener myListener;
    public String date;
    Baby baby;


    void initViews(){

        baby=new Baby();
//        txtDueDate=findViewById(R.id.textViewDueDate);
        listView=findViewById(R.id.listView);
        vaccineList=new ArrayList<>();

        VaccinationDetails vacc1=new VaccinationDetails("BCG","");
        VaccinationDetails vacc2=new VaccinationDetails("DTP","");
        VaccinationDetails vacc3=new VaccinationDetails("HepA","");
        VaccinationDetails vacc4=new VaccinationDetails("HepB","");
        VaccinationDetails vacc5=new VaccinationDetails("Hib","");
        VaccinationDetails vacc6=new VaccinationDetails("IPV","");
        VaccinationDetails vacc7=new VaccinationDetails("Influenza","");
        VaccinationDetails vacc8=new VaccinationDetails("MMR","");
        VaccinationDetails vacc9=new VaccinationDetails("Measles","");
        VaccinationDetails vacc10=new VaccinationDetails("OPV","");
        VaccinationDetails vacc11=new VaccinationDetails("PCV","");
        VaccinationDetails vacc12=new VaccinationDetails("Rota","");
        VaccinationDetails vacc13=new VaccinationDetails("Typhoid","");
        VaccinationDetails vacc14=new VaccinationDetails("Varicella","");
        VaccinationDetails vacc15=new VaccinationDetails("TDAP","");

        vaccineList.add(vacc1);
        vaccineList.add(vacc2);
        vaccineList.add(vacc3);
        vaccineList.add(vacc4);
        vaccineList.add(vacc5);
        vaccineList.add(vacc6);
        vaccineList.add(vacc7);
        vaccineList.add(vacc8);
        vaccineList.add(vacc9);
        vaccineList.add(vacc10);
        vaccineList.add(vacc11);
        vaccineList.add(vacc12);
        vaccineList.add(vacc13);
        vaccineList.add(vacc14);
        vaccineList.add(vacc15);



        vaccineDetailsAdapter=new VaccineDetailsAdapter(this,R.layout.vaccine_list,vaccineList);
        listView.setAdapter(vaccineDetailsAdapter);
        listView.setOnItemClickListener(this);

        Intent rcv=getIntent();

//         date.setTime(rcv.getLongExtra("keyDueDate",-1));
//        baby=(Baby)rcv.getSerializableExtra("keyDueDate");
//        date=rcv.getExtras().getString("keyDueDate");



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_knowledge);
        initViews();
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent=new Intent(VaccinationKnowledgeActivity.this,VaccineDetailsActivity.class);
        intent.putExtra("keyIndex",position);
        startActivity(intent);
    }
}