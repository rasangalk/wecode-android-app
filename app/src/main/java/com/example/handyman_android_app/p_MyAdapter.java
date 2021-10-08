//package com.example.handyman_android_app;
//
//
//        import android.content.Context;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import androidx.annotation.NonNull;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import java.util.ArrayList;
//
//public class p_MyAdapter extends RecyclerView.Adapter<p_MyAdapter.MyViewHolder> {
//
//    Context context;
//    ArrayList<p_User> list;
//
//
//    public p_MyAdapter(Context context, ArrayList<p_User> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.p_request_list,parent,false);
//        return  new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        p_User user = list.get(position);
//        holder.customer.setText(user.customer);
//        holder.title.setText(user.title);
//        holder.location.setText(user.location);
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView customer, title, location;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            customer = itemView.findViewById(R.id.tvlastName);
//            title = itemView.findViewById(R.id.tvfirstName);
//            location = itemView.findViewById(R.id.tvage);
//
//        }
//    }
//
//}
