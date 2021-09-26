package com.example.handyman_android_app.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handyman_android_app.Adapters.GigAdapter;
import com.example.handyman_android_app.CreateGig;
import com.example.handyman_android_app.Model.GigModel;
import com.example.handyman_android_app.R;
import com.example.handyman_android_app.databinding.FragmentHomeBinding;
import com.example.handyman_android_app.loginActivity;
import com.example.handyman_android_app.signupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.example.handyman_android_app.r_activeGigPreview.titleTV;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<GigModel> arrayList;
    private GigAdapter gigAdapter;

    private Button createNew;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =  new ViewModelProvider(this).get(HomeViewModel.class);




        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createNew=root.findViewById(R.id.r_btn_activeGigs_createNew);

        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CreateGig.class));
            }
        });

        context=getContext();
        arrayList=new ArrayList<>();

        recyclerView=root.findViewById(R.id.r_recycleView_activeGigs);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).collection("Gigs");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                if (task.isSuccessful() && task.getResult()!=null){

                    for (DocumentSnapshot data:task.getResult()){

                        GigModel gigModel=new GigModel(data.getDouble("GigID"),
                                data.getString("category"),
                                data.getString("description"),
                                data.getString("location"),
                                data.getString("title"));

                        arrayList.add(gigModel);

                    }

                    gigAdapter=new GigAdapter(arrayList,context);
                    recyclerView.setAdapter(gigAdapter);


                }

            }
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}