package com.tavleen.myproject.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Baby implements Serializable {

    public String name;
    public Date dob;
    public String gender;
    public String docId;
    public ArrayList<Vaccination> vaccinations;


    public Baby() {
    }

    public Baby(String name, Date dob, String gender, String docId, ArrayList<Vaccination> vaccinations) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.docId = docId;
        this.vaccinations = vaccinations;
    }

    @Override
    public String toString() {
        return "Baby{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", docId='" + docId + '\'' +
                ", vaccinations=" + vaccinations +
                '}';
    }
}
