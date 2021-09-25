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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class p_view_response extends AppCompatActivity {

    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_LOCATION= "location";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CATEGORY="category";


    private Button button;

    private TextView textViewDescription;
    private TextView textViewLocation;
    private TextView textViewTitle;
    private TextView textViewCategory;

    String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    // private DocumentReference docRef = db.collection("Users").document("RequestedServices");

    DocumentReference docRef=FirebaseFirestore.getInstance().collection("Users")
            .document(userId).collection("Responses").document("210925.140139");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pview_response);


        button = (Button) findViewById(R.id.p_btn_editResponse_btn);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openRequestServices();
            }
        });

//        button = (Button) findViewById(R.id.p_btn_editResponse_btn);
//        button.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                openRequestServices();
//            }
//        });


        textViewDescription = findViewById(R.id.p_tv1_viewResponse_description);
        textViewLocation = findViewById(R.id.p_tv1_viewResponse_location);
        textViewTitle = findViewById(R.id.p_tv1_viewResponse_title);
        textViewCategory = findViewById(R.id.p_tv1_viewResponse_category);

        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String description = documentSnapshot.getString(KEY_DESCRIPTION);
                            String location = documentSnapshot.getString(KEY_LOCATION);
                            String category = documentSnapshot.getString(KEY_CATEGORY);
                            String title = documentSnapshot.getString(KEY_TITLE);

                            textViewDescription.setText(description);
                            textViewLocation.setText(location);
                            textViewTitle.setText(title);
                            textViewCategory.setText(category);
                        }else {
                            Toast.makeText(p_view_response.this, "Document does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    public void onFailure(Exception e){
                        Toast.makeText(p_view_response.this, "Error",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void openRequestServices() {
        Intent intent = new Intent(this, p_edit_response.class);
        startActivity(intent);


    public void deleteResponse(View view) {
        docRef.delete();

    }
//    private void openRequestServices() {
//        Intent intent = new Intent(this, p_edit_response.class);
//        startActivity(intent);
//    }
}