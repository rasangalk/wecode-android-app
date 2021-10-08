package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class n_view_category extends AppCompatActivity {
    
    private CardView cat1, cat2, cat3, cat4, cat5, cat6, cat7;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_view_category);

        cat1 = (CardView) findViewById(R.id.cat1);
        cat2 = (CardView) findViewById(R.id.cat2);
        cat3 = (CardView) findViewById(R.id.cat3);
        cat4 = (CardView) findViewById(R.id.cat4);
        cat5 = (CardView) findViewById(R.id.cat5);
        cat6 = (CardView) findViewById(R.id.cat6);

        cat1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCat1();
            }
        });
        cat2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCat2();
            }
        });      
        cat3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCat3();
            }
        });      
        cat4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCat4();
            }
        });      
        cat5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCat5();
            }
        });      
        cat6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCat6();
            }
        });
    }

    private void openCat6() {
        Intent intent = new Intent(this, n_view_gig_list.class);
        intent.putExtra("category","Electrician");
        startActivity(intent);
    }

    private void openCat5() {
        Intent intent = new Intent(this, n_view_gig_list.class);
        intent.putExtra("category","Carpenter");
        startActivity(intent);
    }

    private void openCat4() {
        Intent intent = new Intent(this, n_view_gig_list.class);
        intent.putExtra("category","Welding");
        startActivity(intent);
    }

    private void openCat3() {
        Intent intent = new Intent(this, n_view_gig_list.class);
        intent.putExtra("category","Painter");
        startActivity(intent);
    }

    private void openCat2() {
        Intent intent = new Intent(this, n_view_gig_list.class);
        intent.putExtra("category","Mason");
        startActivity(intent);
    }

    private void openCat1() {
        Intent intent = new Intent(this, n_view_gig_list.class);
        intent.putExtra("category","Plumber");
        startActivity(intent);
    }
}