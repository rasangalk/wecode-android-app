package com.example.handyman_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class n_request_services extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "n_request_services";

    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_LOCATION= "location";
    private static final String KEY_DATE = "date";
    private static final String KEY_GIGID = "GigID";
    private static final String KEY_REQUEST_ID = "orderId";
    private static final String KEY_USER_ID = "userID";
    private static final String KEY_HANDYMAN_ID = "handymanId";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_CATEGORY = "category";

    private EditText editTextDescription;
    private EditText editTextDate;
    private Spinner n_request_services_spinner;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "(0?[1-9]|1[012]) [.] (0?[1-9]|[12][0-9]|3[01]) [.] ((19|20)\\d\\d)";

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

       // matcher = pattern.matcher(date);

            if (TextUtils.isEmpty(description) || TextUtils.isEmpty(date) ||  location == "Select a location") {
                Log.d(TAG, "Fields cannot be empty!");
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if ( !date.matches("^[0-3]{1}[0-9]{1}.[0-1]{1}[1-2]{1}.[1-9]{1}[0-9]{3}$")) {
                Log.d(TAG, "Invalid date format!");
                Toast.makeText(this, "Invalid date format",Toast.LENGTH_SHORT).show();
                return;
            }

        Intent intent=getIntent();
        String GigID = intent.getStringExtra("GigID");
        String HandymanID = intent.getStringExtra("HandymanID");
        String Category = intent.getStringExtra("Category");
        String Phone = intent.getStringExtra("Phone");

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("RequestedServices").document(generateDate());

        Map<String, Object> RequestedService = new HashMap<>();
        RequestedService.put(KEY_REQUEST_ID,generateDate());
        RequestedService.put(KEY_USER_ID,userId);
        RequestedService.put(KEY_DESCRIPTION,description);
        RequestedService.put(KEY_DATE,date);
        RequestedService.put(KEY_LOCATION,location);
        RequestedService.put(KEY_GIGID,GigID);
        RequestedService.put(KEY_HANDYMAN_ID,HandymanID);
        RequestedService.put(KEY_PHONE,Phone);
        RequestedService.put(KEY_CATEGORY,Category);

        documentReference.set(RequestedService);

        Toast.makeText(this, "Requested successfully",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, n_view_request_service.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}