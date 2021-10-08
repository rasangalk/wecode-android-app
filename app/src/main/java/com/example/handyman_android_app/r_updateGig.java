package com.example.handyman_android_app;

import androidx.annotation.NonNull;
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

import com.example.handyman_android_app.Model.GigModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.example.handyman_android_app.ui.home.HomeFragment.arrayList;
import static com.example.handyman_android_app.ui.home.HomeFragment.gigAdapter;
import static com.example.handyman_android_app.ui.home.HomeFragment.recyclerView;

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
    private TextView ididid;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    String gigID,category,description,title, Location;
    private int position;


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
        ididid=findViewById(R.id.r_tv_GigPreview_ID_Desc);



        Intent intent=getIntent();
        gigID=intent.getStringExtra("GigID");
        category=intent.getStringExtra("Category");
        Location=intent.getStringExtra("Location");
        description=intent.getStringExtra("Description");
        title=intent.getStringExtra("Title");
        position=intent.getIntExtra("Position",0);

        ididid.setText(String.valueOf(gigID));


        editTextTitle.setText(title);
        editTextDescription.setText(description);


    }

    public void updateGig(View v){

        Intent intent=getIntent();

        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String category = spinnerCategory.getSelectedItem().toString();
        String location = spinnerLocation.getSelectedItem().toString();

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("Gigs")
                .document(gigID);

        Map<String, Object> gig = new HashMap<>();
        gig.put(KEY_TITLE,title);
        gig.put(KEY_DESCRIPTION,description);
        gig.put(KEY_CATEGORY,category);
        gig.put(KEY_LOCATION,location);

        documentReference.update(gig).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(r_updateGig.this,"Updated!",Toast.LENGTH_SHORT).show();
                    GigModel newModel=new GigModel(gigID,category,description,location,title);
                    if (arrayList!=null) {
                        arrayList.set(position, newModel);
                        if (recyclerView!=null){
                            recyclerView.post(new Runnable() {
                                @Override
                                public void run() {
                                    gigAdapter.notifyItemChanged(position);
                                }
                            });
                        }
                    }
                    finish();
                }
            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void signOut(){

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(r_updateGig.this,loginActivity.class));
        finish();

    }

}
