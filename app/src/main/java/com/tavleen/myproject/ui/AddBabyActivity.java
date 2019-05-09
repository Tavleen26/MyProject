package com.tavleen.myproject.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tavleen.myproject.R;
import com.tavleen.myproject.adapter.BabyAdapter;
import com.tavleen.myproject.adapter.VaccineAdapter;
import com.tavleen.myproject.model.Baby;
import com.tavleen.myproject.model.Util;
import com.tavleen.myproject.model.Vaccination;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddBabyActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView mTextMessage;

    TextView txtTitle;
    TextView txtGender;
    EditText eTxtName, eTxtDOB;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;




    boolean updateMode;
    Button btnAdd;


    DatePickerDialog datePickerDialog;

    Baby baby;
    FirebaseAuth auth;
    ProgressDialog progressDialog;

    FirebaseFirestore db;
    FirebaseUser firebaseUser;

    BabyAdapter babyAdapter;
    Vaccination vaccination;

    VaccineAdapter vaccineAdapter;
    ListView listView;
    ArrayList<Vaccination> vaccinationArrayList;


    void initViews() {

        txtTitle=findViewById(R.id.textViewTitle);
        eTxtName = findViewById(R.id.editTextName);
        eTxtDOB = findViewById(R.id.editTextDateOfBirth);
        rgGender = new RadioGroup(this);

        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);
        btnAdd = findViewById(R.id.buttonAddBaby);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        baby = new Baby();
        vaccination=new Vaccination();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = auth.getCurrentUser();



        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);

        btnAdd.setOnClickListener(clickListener);
//        showDatePickerDialog();
        eTxtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Intent rcv=getIntent();
        updateMode=rcv.hasExtra("keyBaby");

        if(updateMode){


            baby=(Baby)rcv.getSerializableExtra("keyBaby");


            txtTitle.setText("Update Baby");
            eTxtName.setText(baby.name);
            eTxtDOB.setText(new SimpleDateFormat("MM-dd-yyyy").format(baby.dob));

            btnAdd.setText("Update Baby");



        }


    }

    Date pickedDate=null;
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            try {
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat format = new SimpleDateFormat(pattern);

                pickedDate = format.parse(year + "-" + (month + 1) + "-" + dayOfMonth);
//                Log.i("TEST","Picked Date year is:"+pickedDate.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String date = year + "/" + (month + 1) + "/" + dayOfMonth;
            eTxtDOB.setText(date);


        }
    };


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent intent=new Intent(AddBabyActivity.this,MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initViews();
    }

    void showDatePickerDialog() {


        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);


        datePickerDialog = new DatePickerDialog(this, onDateSetListener, yy, mm, dd);
        datePickerDialog.show();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            baby.name = eTxtName.getText().toString();
            baby.dob = pickedDate;

//            Log.i("TEST","Picked Date year is:"+pickedDate);


            if (Util.isInternetConnected(AddBabyActivity.this)) {


                babyDetails();
            } else {
                Toast.makeText(AddBabyActivity.this, "Please connect to Internet and Try Again", Toast.LENGTH_LONG).show();
            }


        }
    };

    void saveUserInCloud() {

        if (updateMode) {
            db.collection("user").document(firebaseUser.getUid()).collection("baby").document(baby.docId).set(baby).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isComplete()) {
                        babyAdapter.notifyDataSetChanged();
                        Toast.makeText(AddBabyActivity.this, "Updation Finished", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(AddBabyActivity.this, "Updation Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {

            db.collection("user").document(firebaseUser.getUid()).collection("baby").add(baby).addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {

                public void onComplete(@NonNull Task<DocumentReference> task) {

                    if (task.isComplete()) {

                        String babyId = task.getResult().getId();


//                                 babyDetails();

                        db.collection("user").document(firebaseUser.getUid()).collection("baby").document(babyId).set(baby.docId).addOnCompleteListener(AddBabyActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isComplete()){

                                }


                            }
                        });



                    }

                }

            });



        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id=buttonView.getId();
        switch (id){

            case R.id.radioButtonMale:
                if(isChecked){
                    Toast.makeText(AddBabyActivity.this, "You Selected Male", Toast.LENGTH_LONG).show();
                    baby.gender="Male";
                }
                break;
            case R.id.radioButtonFemale:
                if(isChecked){
                    Toast.makeText(AddBabyActivity.this, "You Selected Female", Toast.LENGTH_LONG).show();
                    baby.gender="Female";
                }
                break;
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,101,1,"All Babies");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==101){
            Intent intent=new Intent(AddBabyActivity.this,AllBabiesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    void clearFields(){
        eTxtName.setText("");
        eTxtDOB.setText("");
    }
    void babyDetails(){

        Calendar call =Calendar.getInstance();
        call.setTime(pickedDate);

        int dd = call.get(Calendar.DAY_OF_MONTH);

        int mm = call.get(Calendar.MONTH);

        int yy = call.get(Calendar.YEAR);



        Calendar babyBirthCal = Calendar.getInstance();

        babyBirthCal.set(Calendar.DAY_OF_MONTH, dd);

        babyBirthCal.set(Calendar.MONTH, mm);

        babyBirthCal.set(Calendar.YEAR, yy);



        Date babyBirthDate = babyBirthCal.getTime();



        Vaccination v1 = new Vaccination("BCG",vaccination.vaccinationDate ,0);

        Vaccination v2 = new Vaccination("OPV-0" ,vaccination.vaccinationDate,0);

        Vaccination v3 = new Vaccination("Hepatitis B1" ,vaccination.vaccinationDate,0);

        Vaccination v4 = new Vaccination("DTwP-1" ,vaccination.vaccinationDate,1);

        Vaccination v5 = new Vaccination("IPV-1" ,vaccination.vaccinationDate,1);

        Vaccination v6 = new Vaccination("OPV*" ,vaccination.vaccinationDate,1);

        Vaccination v7 = new Vaccination("Hib-1" ,vaccination.vaccinationDate,1);

        Vaccination v8 = new Vaccination("Hepatitis B-2" ,vaccination.vaccinationDate,1);

        Vaccination v9 = new Vaccination("Rotavirus-1" ,vaccination.vaccinationDate,1);

        Vaccination v10 = new Vaccination("PCV-1" ,vaccination.vaccinationDate,1);

        Vaccination v11 = new Vaccination("DTwP-2" ,vaccination.vaccinationDate,2);

        Vaccination v12 = new Vaccination("IPV-2" ,vaccination.vaccinationDate,2);

        Vaccination v13 = new Vaccination("OPV*" ,vaccination.vaccinationDate,2);

        Vaccination v14 = new Vaccination("Hib-2" ,vaccination.vaccinationDate,2);

        Vaccination v15 = new Vaccination("Rotavirus-2" ,vaccination.vaccinationDate,2);

        Vaccination v16 = new Vaccination("PCV-2" ,vaccination.vaccinationDate,2);

        Vaccination v17 = new Vaccination("DTwP-3" ,vaccination.vaccinationDate,3);

        Vaccination v18 = new Vaccination("IPV-3" ,vaccination.vaccinationDate,3);

        Vaccination v19 = new Vaccination("OPV*" ,vaccination.vaccinationDate,3);

        Vaccination v20 = new Vaccination("Hib-3" ,vaccination.vaccinationDate,3);

        Vaccination v21 = new Vaccination("Rotavirus-3" ,vaccination.vaccinationDate,3);

        Vaccination v22 = new Vaccination("PCV-3" ,vaccination.vaccinationDate,3);

        Vaccination v23 = new Vaccination("Hepatitis B-3" ,vaccination.vaccinationDate,6);

        Vaccination v24 = new Vaccination("OPV" ,vaccination.vaccinationDate,6);

        Vaccination v25 = new Vaccination("MMR-1" ,vaccination.vaccinationDate,9);

        Vaccination v26 = new Vaccination("OPV" ,vaccination.vaccinationDate,9);

        Vaccination v27 = new Vaccination("Typhoid Conjugate" ,vaccination.vaccinationDate,10);

        Vaccination v28 = new Vaccination("Hepatitis A-1" ,vaccination.vaccinationDate,12);

        Vaccination v29 = new Vaccination("MMR-2" ,vaccination.vaccinationDate,15);

        Vaccination v30 = new Vaccination("Varicella" ,vaccination.vaccinationDate,15);

        Vaccination v31 = new Vaccination("PCV Booster" ,vaccination.vaccinationDate,15);

        Vaccination v32 = new Vaccination("DTwP" ,vaccination.vaccinationDate,18);

        Vaccination v33 = new Vaccination("IPV Booster" ,vaccination.vaccinationDate,18);

        Vaccination v34 = new Vaccination("OPV" ,vaccination.vaccinationDate,18);

        Vaccination v35 = new Vaccination("Hib Booster" ,vaccination.vaccinationDate,18);

        Vaccination v36 = new Vaccination("Hepatitis A-2" ,vaccination.vaccinationDate,18);

        Vaccination v37 = new Vaccination("Varicella-2" ,vaccination.vaccinationDate,21);

        Vaccination v38 = new Vaccination("Typhoid Conjugate" ,vaccination.vaccinationDate,24);

        Vaccination v39 = new Vaccination("DTwP" ,vaccination.vaccinationDate,60);

        Vaccination v40 = new Vaccination("OPV" ,vaccination.vaccinationDate,60);

        Vaccination v41 = new Vaccination("Typhoid" ,vaccination.vaccinationDate,60);

        Vaccination v42 = new Vaccination("Typhoid" ,vaccination.vaccinationDate,96);

        Vaccination v43 = new Vaccination("HPV-1" ,vaccination.vaccinationDate,108);

        Vaccination v44 = new Vaccination("HPV-2" ,vaccination.vaccinationDate,108);

        Vaccination v45 = new Vaccination("HPV-3" ,vaccination.vaccinationDate,108);

        Vaccination v46 = new Vaccination("Tdap" ,vaccination.vaccinationDate,120);

        Vaccination v47 = new Vaccination("Typhoid" ,vaccination.vaccinationDate,132);

        Vaccination v48 = new Vaccination("Typhoid" ,vaccination.vaccinationDate,168);

        Vaccination v49 = new Vaccination("Td" ,vaccination.vaccinationDate,180);




        baby.vaccinations = new ArrayList<>();
        baby.vaccinations.add(v1);
        baby.vaccinations.add(v2);
        baby.vaccinations.add(v3);
        baby.vaccinations.add(v4);
        baby.vaccinations.add(v5);
        baby.vaccinations.add(v6);
        baby.vaccinations.add(v7);
        baby.vaccinations.add(v8);
        baby.vaccinations.add(v9);

        baby.vaccinations.add(v10);
        baby.vaccinations.add(v11);
        baby.vaccinations.add(v12);
        baby.vaccinations.add(v13);
        baby.vaccinations.add(v14);
        baby.vaccinations.add(v15);
        baby.vaccinations.add(v16);
        baby.vaccinations.add(v17);
        baby.vaccinations.add(v18);


        baby.vaccinations.add(v19);
        baby.vaccinations.add(v20);
        baby.vaccinations.add(v21);
        baby.vaccinations.add(v22);
        baby.vaccinations.add(v23);
        baby.vaccinations.add(v24);
        baby.vaccinations.add(v25);
        baby.vaccinations.add(v26);
        baby.vaccinations.add(v27);

        baby.vaccinations.add(v28);
        baby.vaccinations.add(v29);
        baby.vaccinations.add(v30);
        baby.vaccinations.add(v31);
        baby.vaccinations.add(v32);
        baby.vaccinations.add(v33);
        baby.vaccinations.add(v34);
        baby.vaccinations.add(v35);
        baby.vaccinations.add(v36);


        baby.vaccinations.add(v37);
        baby.vaccinations.add(v38);
        baby.vaccinations.add(v39);
        baby.vaccinations.add(v40);
        baby.vaccinations.add(v41);
        baby.vaccinations.add(v42);
        baby.vaccinations.add(v43);
        baby.vaccinations.add(v44);
        baby.vaccinations.add(v45);

        baby.vaccinations.add(v46);
        baby.vaccinations.add(v47);
        baby.vaccinations.add(v48);
        baby.vaccinations.add(v49);







        for (Vaccination v : baby.vaccinations){

            Calendar cal = (Calendar)babyBirthCal.clone();

            cal.add(Calendar.MONTH, v.monthsAfterBirth);
//             cal.add(Calendar.DAY_OF_MONTH,1);
            cal.get(Calendar.DAY_OF_MONTH);
            cal.get(Calendar.YEAR);




            v.vaccinationDate = cal.getTime();



            vaccination.vaccinationDate=v.vaccinationDate;


        }

        saveUserInCloud();

        clearFields();

    }
}
