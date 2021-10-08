package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class n_view_request_service extends AppCompatActivity{

    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_LOCATION= "location";
    private static final String KEY_DATE = "date";

    private Button button;

    String gigID, orderID;

    private TextView textViewDescription;
    private TextView textViewLocation;
    private TextView textViewDate;

    DocumentReference docRef;

//    String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_view_request_service);

        Intent intent=getIntent();
        String GigID = intent.getStringExtra("GigID");
        String OrderID = intent.getStringExtra("RequestID");
        String description = intent.getStringExtra("Description");
        String location = intent.getStringExtra("Location");
        String date = intent.getStringExtra("Date");

        button = (Button) findViewById(R.id.n_btn_view_request_service_update);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openRequestServices();
            }
        });

        textViewDescription = findViewById(R.id.n_tv_view_request_service_description);
        textViewLocation = findViewById(R.id.n_tv_view_request_service_location);
        textViewDate = findViewById(R.id.n_tv_view_request_service_date);

        gigID = GigID;
        orderID = OrderID;

        textViewDescription.setText(description);
        textViewLocation.setText(location);
        textViewDate.setText(date);

//        docRef.get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if(documentSnapshot.exists()){
//                            String description = documentSnapshot.getString(KEY_DESCRIPTION);
//                            String location = documentSnapshot.getString(KEY_LOCATION);
//                            String date = documentSnapshot.getString(KEY_DATE);
//
//                            textViewDescription.setText(description);
//                            textViewLocation.setText(location);
//                            textViewDate.setText(date);
//                        }else {
//                            Toast.makeText(n_view_request_service.this, "Document does not exist",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener(){
//                    public void onFailure(Exception e){
//                        Toast.makeText(n_view_request_service.this, "Error",Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
    public void deleteRequestedServices(View v) {
        docRef = FirebaseFirestore.getInstance().collection("RequestedServices")
            .document(orderID);
        docRef.delete();

        Toast.makeText(n_view_request_service.this, "Request deleted successfully",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, n_view_request_services_list.class);
        startActivity(intent);
    }

    private void openRequestServices() {
        Intent intent = new Intent(this, n_edit_request_service.class);
        intent.putExtra("RequestID",orderID);
        startActivity(intent);
    }
}