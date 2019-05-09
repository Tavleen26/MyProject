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

import java.util.ArrayList;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.viewHolder> {
    Context context;
    int resource;
    ArrayList<Baby> objects;
    OnRecyclerItemClickListener recyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener recyclerItemClickListener){
        this.recyclerItemClickListener=recyclerItemClickListener;

    }

    public BabyAdapter(Context context, int resource, ArrayList<Baby> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public BabyAdapter.viewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(resource,parent,false);
        final BabyAdapter.viewHolder holder=new BabyAdapter.viewHolder(view);
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

       Baby baby=objects.get(position);

        // Extracting Data from News Object and setting the data on the list_item


        holder.txtName.setText(baby.name);
        holder.txtDOB.setText(baby.dob.toString());
        holder.txtGender.setText(baby.gender);

    }


    public int getItemCount() {
        // how many items we wish to have in recycler view
        return objects.size();
    }

    // Nested Class : to hold view of list_item

    class viewHolder extends  RecyclerView.ViewHolder{

        // Attributes of viewHolder

        TextView txtName;
        TextView txtDOB;
        TextView txtGender;
        public viewHolder( View itemView) {
            super(itemView);

//            imageView=itemView.findViewById(R.id.imageView);
            txtName=itemView.findViewById(R.id.textViewName);
            txtDOB=itemView.findViewById(R.id.textViewDOB);
            txtGender=itemView.findViewById(R.id.textViewGender);
        }
    }


}
