package com.example.mymethodistapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.mymethodistapplication.databinding.ActivityAbout1Binding;
import com.example.mymethodistapplication.databinding.ActivityPhotos3Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Photos3 extends AppCompatActivity {
    private DatabaseReference myref;

    private ArrayList<Messages> messagesArrayList;
    private RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    private RecyclerAdpater recyclerAdpater;
    private Context mContext;
    ActivityPhotos3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos3);

        binding = ActivityPhotos3Binding.inflate(getLayoutInflater());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(bottomNavigationView.LABEL_VISIBILITY_LABELED);


        LinearLayoutManager LayoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setHasFixedSize(true);

        myref = FirebaseDatabase.getInstance().getReference();

        messagesArrayList = new ArrayList<>();

        GetDataFromFirebase();

        ClearAll();



    }

    private void GetDataFromFirebase() {

        Query query = myref.child("message");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Messages messages = new Messages();

                    messages.setImageUrl(snapshot.child("image").getValue().toString());
                    messages.setName(snapshot.child("name").getValue().toString());

                    messagesArrayList.add(messages);
                }
                recyclerAdpater = new RecyclerAdpater(mContext,messagesArrayList);
                recyclerView.setAdapter(recyclerAdpater);
                recyclerAdpater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ClearAll(){
        if(messagesArrayList != null){
            messagesArrayList.clear();

            if(recyclerAdpater != null){
                recyclerAdpater.notifyDataSetChanged();
            }
        }
        messagesArrayList = new ArrayList<>();
    }
}