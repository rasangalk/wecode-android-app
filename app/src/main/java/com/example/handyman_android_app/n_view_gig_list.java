package com.example.handyman_android_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.handyman_android_app.Adapters.n_giglist_adapter;
import com.example.handyman_android_app.Model.n_giglist_model;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class n_view_gig_list extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseFirestore database;
    n_giglist_adapter ngiglistadapter;
    ArrayList<n_giglist_model> list;

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_view_gig_list);

        recyclerView = findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        ngiglistadapter = new n_giglist_adapter(com.example.handyman_android_app.n_view_gig_list.this,list);

        recyclerView.setAdapter(ngiglistadapter);


        Intent intent=getIntent();
        category = intent.getStringExtra("category");

        EventChangeListener();
    }

    private void EventChangeListener(){

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        if(category != null){
            database.collection("Gigs").whereEqualTo("category", category)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Log.e("Error", error.getMessage());
                                return;
                            }

                            for (DocumentSnapshot data : value.getDocuments()) {

                                n_giglist_model gigModel = new n_giglist_model(
                                        data.getString("GigID"),
                                        data.getString("UserID"),
                                        data.getString("category"),
                                        data.getString("description"),
                                        data.getString("location"),
                                        data.getString("title"));

                                list.add(gigModel);

                            }
                        }
                    });
        }
        else {
            database.collection("Gigs")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Log.e("Error", error.getMessage());
                                return;
                            }

                            for (DocumentSnapshot data : value.getDocuments()) {

                                n_giglist_model gigModel = new n_giglist_model(
                                        data.getString("GigID"),
                                        data.getString("UserID"),
                                        data.getString("category"),
                                        data.getString("description"),
                                        data.getString("location"),
                                        data.getString("title"));

                                list.add(gigModel);
                            }
                        }
                    });
        }
    }
}
