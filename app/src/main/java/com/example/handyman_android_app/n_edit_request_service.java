package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

    DocumentReference docRef=FirebaseFirestore.getInstance().collection("Users")
            .document(userId).collection("RequestedServices").document("210925.164441");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_edit_request_service);
//
//        Spinner n_edit_request_services_spinner = findViewById(R.id.n_edit_request_services_spinner);
//        n_edit_request_services_spinner.setOnItemSelectedListener(this);

        textViewDescription = findViewById(R.id.n_et_edit_request_description);
       // textViewLocation = findViewById(R.id.n_edit_request_services_spinner);
        textViewDate = findViewById(R.id.n_et_edit_request_services_date);

        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String description = documentSnapshot.getString(KEY_DESCRIPTION);
               //             String location = documentSnapshot.getString(KEY_LOCATION);
                            String date = documentSnapshot.getString(KEY_DATE);

                            textViewDescription.setText(description);
                          //  textViewLocation.setOnItemSelectedListener(getSelectedItem(location).toString());
                            textViewDate.setText(date);
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

        docRef.update(KEY_DESCRIPTION,description);
        docRef.update(KEY_DATE,date);
    }
}