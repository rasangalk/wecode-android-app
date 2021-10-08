package com.example.handyman_android_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handyman_android_app.Model.n_requestlist_model;
import com.example.handyman_android_app.R;
import com.example.handyman_android_app.n_view_request_service;

//import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class n_requestlist_adapter extends RecyclerView.Adapter<n_requestlist_adapter.RequestViewHolder>{

    Context context;
    ArrayList<n_requestlist_model> list;

    public n_requestlist_adapter(Context context, ArrayList<n_requestlist_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.n_request_list,parent,false);
        return new RequestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  n_requestlist_adapter.RequestViewHolder holder, int position) {
        n_requestlist_model n_requestlist_model = list.get(position);
        holder.description.setText(n_requestlist_model.description);
        holder.date.setText(n_requestlist_model.date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, n_view_request_service.class);
                intent.putExtra("GigID",list.get(position).getGigID());
                intent.putExtra("Location",list.get(position).getLocation());
                intent.putExtra("RequestID",list.get(position).getRequestID());
                intent.putExtra("Description",list.get(position).getDescription());
                intent.putExtra("Date",list.get(position).getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
            return list.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{

        TextView description, date;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.n_tv_request_list_description);
            date = itemView.findViewById(R.id.n_tv_request_list_date);
        }
    }
}
