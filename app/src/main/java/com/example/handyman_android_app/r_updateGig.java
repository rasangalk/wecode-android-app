package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class r_updateGig extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "CreateGig";

    private static final String KEY_TITLE= "title";
    private static final String KEY_CATEGORY= "category";
    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_LOCATION= "location";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerCategory;
    private Spinner spinnerLocation;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    String category,description,title, Location,GigID;
    private double gigID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rupdate_gig);

        Spinner gigCategory = findViewById(R.id.r_spin_createGig_category);
        gigCategory.setOnItemSelectedListener(this);

        Spinner location = findViewById(R.id.r_spin_createGig_loation);
        location.setOnItemSelectedListener(this);

        editTextTitle = findViewById(R.id.r_et_crateGig_title);
        editTextDescription = findViewById(R.id.r_et_crateGig_description);
        spinnerCategory = findViewById(R.id.r_spin_createGig_category);
        spinnerLocation = findViewById(R.id.r_spin_createGig_loation);

        Intent intent=getIntent();
        gigID=intent.getDoubleExtra("GigID",0);
        category=intent.getStringExtra("Category");
        Location=intent.getStringExtra("Location");
        description=intent.getStringExtra("Description");
        title=intent.getStringExtra("Title");

        GigID =  Double.toString(gigID);


    }

    public void updateGig(View v){

        Intent intent=getIntent();
      double  gigID=intent.getDoubleExtra("GigID",0);
     String   GigID =  Double.toString(gigID);

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String category = spinnerCategory.getSelectedItem().toString();
        String location = spinnerLocation.getSelectedItem().toString();

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("Users")
                .document(userId).collection("Gigs").document(GigID);

        Map<String, Object> gig = new HashMap<>();
        gig.put(KEY_TITLE,title);
        gig.put(KEY_DESCRIPTION,description);
        gig.put(KEY_CATEGORY,category);
        gig.put(KEY_LOCATION,location);

        documentReference.update(gig);

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}