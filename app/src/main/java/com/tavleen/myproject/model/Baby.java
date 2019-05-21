package com.tavleen.myproject.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Baby implements Serializable {

    public String name;
    public String dob;
    public String gender;
    public String docId;
    public String uId;
    public String token;
    public ArrayList<Vaccination> vaccinations;


    public Baby() {
    }

//    public Baby(String name, Date dob, String gender, String docId, ArrayList<Vaccination> vaccinations) {
//        this.name = name;
//        this.dob = dob;
//        this.gender = gender;
//        this.docId = docId;
//        this.vaccinations = vaccinations;
//    }
//
//    @Override
//    public String toString() {
//        return "Baby{" +
//                "name='" + name + '\'' +
//                ", dob=" + dob +
//                ", gender='" + gender + '\'' +
//                ", docId='" + docId + '\'' +
//                ", vaccinations=" + vaccinations +
//                '}';
//    }


//    public Baby(String name, Date dob, String gender, String docId, String uId, ArrayList<Vaccination> vaccinations) {
//        this.name = name;
//        this.dob = dob;
//        this.gender = gender;
//        this.docId = docId;
//        this.uId = uId;
//        this.vaccinations = vaccinations;
//    }


    public Baby(String name, String dob, String gender, String docId, String uId, String token, ArrayList<Vaccination> vaccinations) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.docId = docId;
        this.uId = uId;
        this.token = token;
        this.vaccinations = vaccinations;
    }
}
