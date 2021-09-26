package com.example.handyman_android_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handyman_android_app.Model.GigModel;
import com.example.handyman_android_app.R;
import com.example.handyman_android_app.r_activeGigPreview;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GigAdapter extends RecyclerView.Adapter<GigAdapter.viewHolder>{

    ArrayList<GigModel> arrayList;
    Context context;

    public GigAdapter(ArrayList<GigModel> arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gig_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {

        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText(arrayList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, r_activeGigPreview.class);
                intent.putExtra("GigID",arrayList.get(position).getGigID());
                intent.putExtra("Category",arrayList.get(position).getCategory());
                intent.putExtra("Location",arrayList.get(position).getLocation());
                intent.putExtra("Description",arrayList.get(position).getDescription());
                intent.putExtra("Title",arrayList.get(position).getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView title,description;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.r_tv_activeGigTitle);
            description=(TextView)itemView.findViewById(R.id.r_tv_activeGigDescription);

        }
    }

}
