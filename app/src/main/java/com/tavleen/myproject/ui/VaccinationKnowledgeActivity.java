package com.tavleen.myproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.tavleen.myproject.R;
import com.tavleen.myproject.adapter.VaccineAdapter;
import com.tavleen.myproject.listener.MyListener;
import com.tavleen.myproject.model.Vaccination;

import java.util.ArrayList;

public class VaccinationKnowledgeActivity extends AppCompatActivity {

    ArrayList<Vaccination> vaccineList;
    ListView listView;
    VaccineAdapter vaccineAdapter;
    MyListener myListener;


    void initViews() {
        listView = findViewById(R.id.listView);
        vaccineList = new ArrayList<>();

//        Vaccination vacc1 = new Vaccination("BCG", "");
//        Vaccination vacc2 = new Vaccination("IPV", "");
//        Vaccination vacc3 = new Vaccination("Influenza Vaccine", "");
//        Vaccination vacc4 = new Vaccination("MMR", "");
//        Vaccination vacc5 = new Vaccination("Measles", "");
//        Vaccination vacc6 = new Vaccination("OPV", "");
//        Vaccination vacc7 = new Vaccination("PCV", "");
//        Vaccination vacc8 = new Vaccination("Rota Virus", "");
//        Vaccination vacc9 = new Vaccination("Typhoid Vaccine", "");
//        Vaccination vacc10 = new Vaccination("Varicella Vaccine", "");
//

//        vaccineList.add(vacc1);
//        vaccineList.add(vacc2);
//        vaccineList.add(vacc3);
//        vaccineList.add(vacc4);
//        vaccineList.add(vacc5);
//        vaccineList.add(vacc6);
//        vaccineList.add(vacc7);
//        vaccineList.add(vacc8);
//        vaccineList.add(vacc9);
//        vaccineList.add(vacc10);


//        vaccineAdapter = new VaccineAdapter(this, R.layout.list_item, vaccineList);
//        listView.setAdapter(vaccineAdapter);
//        listView.setOnItemClickListener(this);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_chart);
        initViews();
    }
}