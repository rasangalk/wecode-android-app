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

        import com.example.handyman_android_app.Model.RequestsModel;
        import com.example.handyman_android_app.R;
        import com.example.handyman_android_app.p_view_request;

        import org.jetbrains.annotations.NotNull;

        import java.lang.reflect.Array;
        import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.viewHolder>{

    ArrayList<RequestsModel> arrayList;
    Context context;

    public RequestAdapter(ArrayList<RequestsModel> arrayList,Context context){
        this.arrayList=arrayList;
        this.context=context;
    }

    @NonNull
    @NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.p_buyer_request_card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewHolder holder, int position) {

        holder.title.setText(arrayList.get(position).getTitle());
        holder.location.setText(arrayList.get(position).getLocation());
        holder.category.setText(arrayList.get(position).getCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, p_view_request.class);
                intent.putExtra("ResponseID",arrayList.get(position).getRequestID());
                intent.putExtra("Category",arrayList.get(position).getCategory());
                intent.putExtra("Location",arrayList.get(position).getLocation());
                intent.putExtra("Description",arrayList.get(position).getDescription());
                intent.putExtra("Title",arrayList.get(position).getTitle());
                intent.putExtra("CustomerName",arrayList.get(position).getCustomerName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView title,category,location;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.p_requestTitle);
            category=(TextView)itemView.findViewById(R.id.p_requestCategory);
            location=(TextView)itemView.findViewById(R.id.p_requestLocation);

        }
    }

}