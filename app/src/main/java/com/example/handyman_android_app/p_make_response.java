package com.example.handyman_android_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class p_make_response extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "p_make_response";



    //    private static final String KEY_CATEGORY= "category";

    private static final String KEY_CATEGORY= "category";

    private static final String KEY_TITLE= "title";
    private static final String KEY_DESCRIPTION= "description";
    private static final String KEY_LOCATION= "location";


    //    private EditText editTextCategory;

    private Spinner category_spinner;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner location_spinner;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmake_response);

        category_spinner = findViewById(R.id.p_spn_makeResponse_category);
        category_spinner.setOnItemSelectedListener(this);

        location_spinner = findViewById(R.id.p_et_makeResponse_location);
        location_spinner.setOnItemSelectedListener(this);

        editTextTitle = findViewById(R.id.p_et_makeResponse_title);
        editTextDescription = findViewById(R.id.p_et_makeResponse_description);

    }

    public String generateDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd.HHmmss");
        double id = Double.parseDouble(String.valueOf(simpleDateFormat.format(Calendar.getInstance().getTime())));
        return String.valueOf(id);
    }

    public void saveNote(View v){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String category = category_spinner.getSelectedItem().toString();
        String location = location_spinner.getSelectedItem().toString();

        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("Users")
                .document(userId).collection("Responses").document(generateDate());

//        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DocumentReference documentReference=FirebaseFirestore.getInstance().collection("Users")
//                .document(userId).collection("RequestedServices").document(generateDate());

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE,title);
        note.put(KEY_DESCRIPTION,description);
        note.put(KEY_CATEGORY,category);
        note.put(KEY_LOCATION,location);

        documentReference.set(note);

        documentReference.set(note);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}





















