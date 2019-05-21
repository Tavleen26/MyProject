package com.tavleen.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tavleen.myproject.R;
import com.tavleen.myproject.listener.OnRecyclerItemClickListener;
import com.tavleen.myproject.model.Baby;
import com.tavleen.myproject.model.Vaccination;
import com.tavleen.myproject.model.VaccinationDetails;

import java.util.ArrayList;


public class VaccineDetailsAdapter extends ArrayAdapter<VaccinationDetails> {
    Context context;
    int resource;
    Baby baby;
    ArrayList<VaccinationDetails> objects;
    OnRecyclerItemClickListener recyclerItemClickListener;


    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;

    }

    public VaccineDetailsAdapter(Context context, int resource, ArrayList<VaccinationDetails> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // view is ref variable which is pointing to list_item
        View view= LayoutInflater.from(context).inflate(resource,parent,false);


        TextView txtVaccineName=view.findViewById(R.id.textViewVaccineName);
//        TextView txtDueDate=view.findViewById(R.id.textViewDueDate);

        VaccinationDetails vaccination=objects.get(position);

        // Extracting Data from News Object and Setting the data on list_item


        txtVaccineName.setText(vaccination.name);
//        txtDueDate.setText(vaccination.dueDate);





        return view;
    }



}