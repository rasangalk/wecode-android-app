package com.example.handyman_android_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import static com.example.handyman_android_app.ui.home.HomeFragment.arrayList;
import static com.example.handyman_android_app.ui.home.HomeFragment.gigAdapter;
import static com.example.handyman_android_app.ui.home.HomeFragment.recyclerView;

public class r_activeGigPreview extends AppCompatActivity {

    String gigID;
    String category,description,title, location;
    public static TextView titleTV,gigIdTV,categoryTV,descriptionTV,locationTV;
    private Button update;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ractive_gig_preview);

        Intent intent=getIntent();
        gigID=intent.getStringExtra("GigID");
        category=intent.getStringExtra("Category");
        location=intent.getStringExtra("Location");
        description=intent.getStringExtra("Description");
        title=intent.getStringExtra("Title");
        position=intent.getIntExtra("Position",0);


        gigIdTV=findViewById(R.id.r_tv_GigPreview_ID_Desc);
        titleTV=findViewById(R.id.r_tv_GigPreview_title_Desc);
        categoryTV=findViewById(R.id.r_tv_GigPreview_category_Desc);
        descriptionTV=findViewById(R.id.r_tv_GigPreview_description_Desc);
        locationTV=findViewById(R.id.r_tv_GigPreview_location_Desc);

        gigIdTV.setText(gigID);
        titleTV.setText(title);
        categoryTV.setText(category);
        descriptionTV.setText(description);
        locationTV.setText(location);

        update=findViewById(R.id.r_btn_GigPreview_update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), r_updateGig.class));
                Intent intent=new Intent(getApplicationContext(), r_updateGig.class);
                intent.putExtra("GigID",gigID);
                intent.putExtra("Title",title);
                intent.putExtra("Category",category);
                intent.putExtra("Description",description);
                intent.putExtra("Location",location);
                intent.putExtra("Position",position);
                startActivity(intent);
                finish();
            }
        });
    }

    public void deleteGig(View v){
         FirebaseFirestore db = FirebaseFirestore.getInstance();
         DocumentReference gigref = db.collection("Gigs").document(gigID);
         gigref.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull @NotNull Task<Void> task) {
                 if (task.isSuccessful()){
                     if (recyclerView!=null){

                         arrayList.remove(position);
                         recyclerView.post(new Runnable() {
                             @Override
                             public void run() {
                                 gigAdapter.notifyItemRemoved(position);
                                 gigAdapter.notifyItemRangeChanged(position,arrayList.size());
                             }
                         });

                     }
                     finish();
                 }
             }
         });
    }
}
