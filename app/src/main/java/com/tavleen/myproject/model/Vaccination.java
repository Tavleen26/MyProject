package com.tavleen.myproject.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vaccination implements Serializable {

    public String name;

    int year,month,dayOfMonth;
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    public Date vaccinationDate;

    {
        try {
            vaccinationDate = format.parse(year + "-" + (month ) + "-" + dayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public int monthsAfterBirth;


    public Vaccination() {
    }


    public Vaccination(String name, Date vaccinationDate, int monthsAfterBirth) {
        this.name = name;
        this.vaccinationDate = vaccinationDate;
        this.monthsAfterBirth = monthsAfterBirth;
    }
}
