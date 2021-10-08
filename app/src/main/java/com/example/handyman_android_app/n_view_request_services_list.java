package com.example.handyman_android_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;


import com.example.handyman_android_app.Adapters.n_requestlist_adapter;

import com.example.handyman_android_app.Model.n_requestlist_model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
public class n_view_request_services_list extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore database;
    n_requestlist_adapter nrequestlistadapter;
    ArrayList<n_requestlist_model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_view_request_services_list);

        recyclerView = findViewById(R.id.requestList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        nrequestlistadapter = new n_requestlist_adapter(com.example.handyman_android_app.n_view_request_services_list.this,list);

        recyclerView.setAdapter(nrequestlistadapter);
        EventChangeListener();
    }


    private void EventChangeListener() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        database.collection("RequestedServices").whereEqualTo("userID",userId )
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Error", error.getMessage());
                            return;
                        }

                        for (DocumentSnapshot data : value.getDocuments()) {

                            n_requestlist_model requestModel = new n_requestlist_model(
                                    data.getString("GigID"),
                                    data.getString("date"),
                                    data.getString("description"),
                                    data.getString("location"),
                                    data.getString("orderId"));

                           list.add(requestModel);
                        }
                    }
                });
    }
}