package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class n_request_services extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "n_request_services";

    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_LOCATION= "location";
    private static final String KEY_DATE = "date";

    private EditText editTextDescription;
    private EditText editTextDate;
    private Spinner n_request_services_spinner;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_activity_request_services);

        n_request_services_spinner = findViewById(R.id.n_request_services_spinner);
        n_request_services_spinner.setOnItemSelectedListener(this);

        editTextDescription = findViewById(R.id.n_et_description);
        editTextDate = findViewById(R.id.n_et_date);
    }

    public String generateDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd.HHmmss");
        double id = Double.parseDouble(String.valueOf(simpleDateFormat.format(Calendar.getInstance().getTime())));

        return String.valueOf(id);
    }

    public void requestService(View v) {
        String description = editTextDescription.getText().toString();
        String date = editTextDate.getText().toString();
        String location = n_request_services_spinner.getSelectedItem().toString();

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("Users")
                .document(userId).collection("RequestedServices").document(generateDate());

        Map<String, Object> RequestedService = new HashMap<>();
        RequestedService.put(KEY_DESCRIPTION,description);
        RequestedService.put(KEY_DATE,date);
        RequestedService.put(KEY_LOCATION,location);

        documentReference.set(RequestedService);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}