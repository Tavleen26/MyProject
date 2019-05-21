package com.tavleen.myproject.model;

import java.util.Date;

public class VaccinationDetails {

    public String name;

    public String date;

    public VaccinationDetails() {
    }

    public VaccinationDetails(String name, String date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "VaccinationDetails{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
