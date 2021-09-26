package com.example.handyman_android_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class n_view_gig_list extends AppCompatActivity {


        RecyclerView recyclerView;
        FirebaseFirestore database;
        MyAdapter myAdapter;
        ArrayList<n_gig> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.n_activity_view_gig_list);

            recyclerView = findViewById(R.id.userList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            database = FirebaseFirestore.getInstance();
            list = new ArrayList<>();
            myAdapter = new MyAdapter(com.example.handyman_android_app.n_view_gig_list.this,list);

            recyclerView.setAdapter(myAdapter);
            EventChangeListener();
        }

        private void EventChangeListener(){
            String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

            database.collection("Users") .document(userId).collection("Gigs").orderBy("title", Query.Direction.ASCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                            if(error!=null){
                                Log.e("Error", error.getMessage());
                                return;
                            }
                            for(DocumentChange dc: value.getDocumentChanges()){
                                if(dc.getType()== DocumentChange.Type.ADDED) {
                                    list.add(dc.getDocument().toObject(n_gig.class));
                                }
                                myAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }


}