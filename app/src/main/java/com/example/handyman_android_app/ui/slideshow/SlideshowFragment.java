//package com.example.handyman_android_app.ui.slideshow;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.handyman_android_app.Adapters.n_gig_list_adapter;
//import com.example.handyman_android_app.n_gig_list_model;
//import com.example.handyman_android_app.R;
//import com.example.handyman_android_app.databinding.FragmentSlideshowBinding;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import org.jetbrains.annotations.NotNull;
//import java.util.ArrayList;
//import java.util.Objects;
//
//
//public class SlideshowFragment extends Fragment {
//
//    private SlideshowViewModel slideshowViewModel;
//    private FragmentSlideshowBinding binding;
//    private RecyclerView recyclerView;
//    private Context context;
//    private ArrayList<n_gig_list_model> arrayList;
//    private n_gig_list_adapter gigAdapter;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//        slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);
//
//        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        context=getContext();
//        arrayList=new ArrayList<>();
//
//        recyclerView=root.findViewById(R.id.userList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//
//        CollectionReference collectionReference = FirebaseFirestore.getInstance().collection("Users").document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).collection("Gigs");
//        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//           @Override
//            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//
//                if (task.isSuccessful() && task.getResult()!=null){
//
//                    for (DocumentSnapshot data:task.getResult()){
//
//                        n_gig_list_model n_gig_list = new n_gig_list_model(
//                                data.getDouble("GigID"),
//                                data.getString("category"),
//                                data.getString("description"),
//                                data.getString("location"),
//                                data.getString("title"));
//
//                        arrayList.add(n_gig_list);
//                    }
//
//                    gigAdapter=new n_gig_list_adapter(arrayList,context);
//                    recyclerView.setAdapter(gigAdapter);
//                }
//            }
//        });
//
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}