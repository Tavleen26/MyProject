package com.tavleen.myproject.model;

import java.io.Serializable;

public class MainVaccine implements Serializable {
    public  int image;
    public String title;

    public MainVaccine() {
    }

    public MainVaccine(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
