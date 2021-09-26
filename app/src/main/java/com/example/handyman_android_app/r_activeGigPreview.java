package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class r_activeGigPreview extends AppCompatActivity {

    public double gigID;
    String category,description,title, location,GigID;
    public static TextView titleTV,gigIdTV,categoryTV,descriptionTV,locationTV;
    private Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ractive_gig_preview);

        Intent intent=getIntent();
        gigID=intent.getDoubleExtra("GigID",0);
        category=intent.getStringExtra("Category");
        location=intent.getStringExtra("Location");
        description=intent.getStringExtra("Description");
        title=intent.getStringExtra("Title");

        GigID = Double.toString(gigID);

        gigIdTV=findViewById(R.id.r_tv_GigPreview_ID_Desc);
        titleTV=findViewById(R.id.r_tv_GigPreview_title_Desc);
        categoryTV=findViewById(R.id.r_tv_GigPreview_category_Desc);
        descriptionTV=findViewById(R.id.r_tv_GigPreview_description_Desc);
        locationTV=findViewById(R.id.r_tv_GigPreview_location_Desc);

        gigIdTV.setText(GigID);
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
                intent.putExtra("GigID",GigID);
                intent.putExtra("Title",title);
                intent.putExtra("Category",category);
                intent.putExtra("Description",description);
                intent.putExtra("Location",location);
                startActivity(intent);
            }
        });
    }

    public void deleteGig(View v){
         FirebaseFirestore db = FirebaseFirestore.getInstance();
         String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
         DocumentReference gigref = db.document("Users/"+userId+"/Gigs/"+gigID);
         gigref.delete();
    }
}