package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class n_edit_request_service extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_LOCATION= "location";
    private static final String KEY_DATE = "date";


    private EditText textViewDescription;
    private Spinner textViewLocation;
    private EditText textViewDate;

    String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_edit_request_service);

        Spinner spinnerLocation = findViewById(R.id.n_edit_request_services_spinner);
        spinnerLocation.setOnItemSelectedListener(this);

        Intent intent=getIntent();
        String OrderID = intent.getStringExtra("RequestID");

        textViewDescription = findViewById(R.id.n_et_edit_request_description);
        textViewLocation = findViewById(R.id.n_edit_request_services_spinner);
        textViewDate = findViewById(R.id.n_et_edit_request_services_date);

        docRef=FirebaseFirestore.getInstance()
                .collection("RequestedServices").document(OrderID);

        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String description = documentSnapshot.getString(KEY_DESCRIPTION);
                            String location = (KEY_LOCATION);
                            String date = documentSnapshot.getString(KEY_DATE);


                            textViewDescription.setText(description);
                            spinnerLocation.setOnItemSelectedListener(null);
                            textViewDate.setText(date);

                            Toast.makeText(n_edit_request_service.this, "Updated successfully",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(n_edit_request_service.this, "Document does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    public void onFailure(Exception e){
                        Toast.makeText(n_edit_request_service.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void updateRequestedServices(View view) {
        String description = textViewDescription.getText().toString();
        String date = textViewDate.getText().toString();
        String location = textViewLocation.getSelectedItem().toString();

        docRef.update(KEY_DESCRIPTION,description);
        docRef.update(KEY_LOCATION,location);
        docRef.update(KEY_DATE,date);

        Intent intent = new Intent(this, n_view_request_service.class);
        startActivity(intent);

    }
}