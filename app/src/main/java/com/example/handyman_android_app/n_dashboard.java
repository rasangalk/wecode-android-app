package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class n_dashboard extends AppCompatActivity {

    private CardView c1, c2, c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_dashboard);

        c1 = (CardView) findViewById(R.id.n_dashboard_findHandymen);
        c2 = (CardView) findViewById(R.id.n_dashboard_gigs);
        c3 = (CardView) findViewById(R.id.n_dashboard_requests);

        c1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               openCategories();
            }
        });
        c2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openGigs();
            }
        });
        c3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openRequests();
            }
        });
    }

    private void openRequests() {
        Intent intent = new Intent(this, n_view_request_services_list.class);
        startActivity(intent);
    }

    private void openGigs() {
        Intent intent = new Intent(this, n_view_gig_list.class);
        startActivity(intent);
    }

    private void openCategories() {
        Intent intent = new Intent(this, n_view_category.class);
        startActivity(intent);
    }


}