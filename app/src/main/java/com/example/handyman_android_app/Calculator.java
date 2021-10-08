package com.example.handyman_android_app;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_TITLE = "title";
    private static final String KEY_INCOME = "income";
    private static final String KEY_EXPENSES = "expenses";
    private EditText editTextTitle;
    private EditText editTextIncome;
    private EditText editTextExpenses;
    private TextView textViewData;
    String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
    DocumentReference noteRef=FirebaseFirestore.getInstance().collection("Users")
            .document(userId).collection("RequestedServices").document("Calculator");
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        editTextTitle = findViewById(R.id.calculator_et_title);
        editTextIncome = findViewById(R.id.calculator_et_income);
        editTextExpenses = findViewById(R.id.calculator_et_expenses);
        textViewData = findViewById(R.id.calculator_et_profit);
    }
    @Override
    protected void onStart() {
        super.onStart();
        noteRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(Calculator.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                    return;
                }
                if (documentSnapshot.exists()) {
                    String title = documentSnapshot.getString(KEY_TITLE);
                    String income = documentSnapshot.getString(KEY_INCOME);
                    String expenses = documentSnapshot.getString(KEY_EXPENSES);
                    textViewData.setText("Title: " + title + "\n" + "Description: " + income);
                }
            }
        });
    }

    public void loadNote(View v) {
        String title = editTextTitle.getText().toString();
        String income = editTextIncome.getText().toString();
        String expenses = editTextExpenses.getText().toString();
        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, title);
        note.put(KEY_INCOME, income);
        note.put(KEY_EXPENSES, expenses);
        noteRef.set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Calculator.this, "Record saved", Toast.LENGTH_SHORT).show();
                    }
                });

        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String title = documentSnapshot.getString(KEY_TITLE);
                            String income = documentSnapshot.getString(KEY_INCOME);
                            String expenses = documentSnapshot.getString(KEY_EXPENSES);
                            //Map<String, Object> note = documentSnapshot.getData();
                            int incomeInt = Integer.parseInt(income);
                            int expenseInt = Integer.parseInt(expenses);
                            int profit =  incomeInt - expenseInt;
                            String s=Integer.toString(profit);
                            textViewData.setText(s);
                        } else {
                            Toast.makeText(Calculator.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}




