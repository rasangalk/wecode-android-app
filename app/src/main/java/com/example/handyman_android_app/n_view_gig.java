package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class n_view_gig extends AppCompatActivity {
    private static final String KEY_PHONE= "Phone";
    private static final String KEY_EMAIL= "Email";

    private Button button;
    TextView Category, Title, Phone, Location, Email, Description;
    String gigID, handymanID, cat, phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_view_gig);

        Category = findViewById(R.id.n_tv_view_gig_category);
        Title = findViewById(R.id.n_tv_view_gig_title);
        Location = findViewById(R.id.n_tv_view_gig_location);
        Description=findViewById(R.id.n_tv_view_gig_description);
        Phone = findViewById(R.id.n_tv_view_gig_phone);
        Email = findViewById(R.id.n_tv_view_gig_email);

        Intent intent=getIntent();
        String GigID = intent.getStringExtra("GigID");
        String HandymanID = intent.getStringExtra("HandymanID");
        String title = intent.getStringExtra("Title");
        String description = intent.getStringExtra("Description");
        String location = intent.getStringExtra("Location");
        String category = intent.getStringExtra("Category");

        gigID = GigID;
        handymanID = HandymanID;
        cat = category;

        Category.setText(category);
        Title.setText(title);
        Location.setText(location);
        Description.setText(description);

        String userId= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        DocumentReference docRef= FirebaseFirestore.getInstance().collection("Users")
                .document(userId);

        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String email = documentSnapshot.getString(KEY_EMAIL);
                            String phone = documentSnapshot.getString(KEY_PHONE);

                            Phone.setText(phone);
                            Email.setText(email);

                            phoneNo = phone;

                        }else {
                            Toast.makeText(n_view_gig.this, "Document does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        button = (Button) findViewById(R.id.n_btn_view_gig_request_service);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openRequestServices();
            }
        });
    }
    private void openRequestServices() {
        Intent intent = new Intent(this, n_request_services.class);
        intent.putExtra("GigID",gigID);
        intent.putExtra("HandymanID",handymanID);
        intent.putExtra("Category", cat);
        intent.putExtra("Phone", phoneNo);
        startActivity(intent);
    }
}