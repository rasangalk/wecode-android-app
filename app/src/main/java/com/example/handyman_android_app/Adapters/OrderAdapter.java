package com.example.handyman_android_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handyman_android_app.Model.OrderModel;
import com.example.handyman_android_app.R;
import com.example.handyman_android_app.r_activeGigPreview;
import com.example.handyman_android_app.r_order_preview;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.viewHolder> {

    ArrayList<OrderModel> arrayList24;
    Context context24;

    public OrderAdapter(ArrayList<OrderModel> arrayList24,Context context24){
        this.arrayList24=arrayList24;
        this.context24=context24;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.r_gig_order_item,parent,false);
        return new OrderAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OrderAdapter.viewHolder holder, int position) {
        holder.category.setText(arrayList24.get(position).getCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context24, r_order_preview.class);
                intent.putExtra("Category",arrayList24.get(position).getCategory());
                intent.putExtra("Date",arrayList24.get(position).getDate());
                intent.putExtra("Description",arrayList24.get(position).getDescription());
                intent.putExtra("Location",arrayList24.get(position).getLocation());
                intent.putExtra("OrderId",arrayList24.get(position).getOrderId());
                intent.putExtra("Phone",arrayList24.get(position).getPhone());
                intent.putExtra("Position",position);
                context24.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList24.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView category,date;

        public viewHolder(@NonNull @NotNull View itemView24) {
            super(itemView24);

            category=(TextView)itemView24.findViewById(R.id.r_tv_orderCategory);

        }
    }
}
