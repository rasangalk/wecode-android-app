package com.example.handyman_android_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handyman_android_app.Model.n_giglist_model;
import com.example.handyman_android_app.R;
import com.example.handyman_android_app.n_view_gig;

import java.util.ArrayList;

public class n_giglist_adapter extends RecyclerView.Adapter<n_giglist_adapter.MyViewHolder> {

    Context context;
    ArrayList<n_giglist_model> list;

    public n_giglist_adapter(Context context, ArrayList<n_giglist_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.n_gig_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        n_giglist_model n_giglist_model = list.get(position);
        holder.title.setText(n_giglist_model.title);
        holder.location.setText(n_giglist_model.location);
        holder.category.setText(String.valueOf(n_giglist_model.category));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, n_view_gig.class);
                intent.putExtra("GigID",list.get(position).getGigID());
                intent.putExtra("HandymanID",list.get(position).getUserID());
                intent.putExtra("Category",list.get(position).getCategory());
                intent.putExtra("Location",list.get(position).getLocation());
                intent.putExtra("Description",list.get(position).getDescription());
                intent.putExtra("Title",list.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, location, category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            location = itemView.findViewById(R.id.tvLocation);
            category = itemView.findViewById(R.id.tvCategory);
        }
    }
}