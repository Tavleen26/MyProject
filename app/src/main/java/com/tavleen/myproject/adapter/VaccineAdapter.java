package com.tavleen.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tavleen.myproject.R;
import com.tavleen.myproject.listener.OnRecyclerItemClickListener;
import com.tavleen.myproject.model.Baby;
import com.tavleen.myproject.model.Vaccination;
import com.tavleen.myproject.model.VaccinationDetails;

import java.util.ArrayList;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.viewHolder> {
    Context context;
    int resource;
    Baby baby;
    ArrayList<Vaccination> objects;
    ArrayList<VaccinationDetails>object;
    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
        this.recyclerItemClickListener=recyclerItemClickListener;

    }

    public VaccineAdapter(Context context, int resource, ArrayList<Vaccination> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }





    public VaccineAdapter.viewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(resource,parent,false);
        final VaccineAdapter.viewHolder holder=new VaccineAdapter.viewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder( viewHolder holder, int position) {

//        Baby baby=objects.get(position);

        Vaccination vaccination=objects.get(position);




//        holder.txtName.setText(String.valueOf(baby.vaccinations.get(1)));


//        holder.txtName.setText(String.valueOf(baby.vaccinations.get(0).name));
//        holder.txtDate.setText(String.valueOf(baby.vaccinations.get(0).vaccinationDate));

         holder.txtName.setText("Vaccine Name: "+vaccination.name);
         holder.txtDate.setText("Due Date: "+vaccination.vaccinationDate);

    }


    public int getItemCount() {
        // how many items we wish to have in recycler view
        return objects.size();
    }

    // Nested Class : to hold view of list_item

    class viewHolder extends  RecyclerView.ViewHolder{

        // Attributes of viewHolder

        TextView txtName,txtDate;

        public viewHolder( View itemView) {
            super(itemView);


            txtName=itemView.findViewById(R.id.textViewVaccineName);
            txtDate=itemView.findViewById(R.id.textViewDueDate);


        }
    }


}
