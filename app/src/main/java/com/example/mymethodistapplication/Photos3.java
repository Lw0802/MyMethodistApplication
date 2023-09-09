package com.example.mymethodistapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class Photos3 extends AppCompatActivity {
    private DatabaseReference myref;

    private ArrayList<Messages> messagesArrayList;
    private GridView gridView;
    BottomNavigationView bottomNavigationView;
    private RecyclerAdpater recyclerAdpater;
    private Context mContext;
    ActivityPhotos3Binding binding;
    public int choice;
    public String choice2;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos3);

        Intent intent = getIntent();


        if (intent != null && intent.hasExtra("choiceKey")) {
            choice = intent.getIntExtra("choiceKey",0);
        }

        TextView heading = findViewById(R.id.textView37);
        if (heading != null) {
            if (choice == 0) {
                choice2 = "Images2";
                heading.setText("2023");

            } else if (choice == 1) {
                choice2 = "Images";
                heading.setText("2022");
            }
        }


        binding = ActivityPhotos3Binding.inflate(getLayoutInflater());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(bottomNavigationView.LABEL_VISIBILITY_LABELED);

// In your About5 activity, after initializing Firebase
        DatabaseReference imagesReference = databaseReference.child(choice2);

        GridView gridView = findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        imagesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> imageLinks = new ArrayList<>();


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String link = snapshot.getValue(String.class);
                    imageLinks.add(link);
                }

                // Update the adapter with the image URLs
                imageAdapter.setImageUrls(imageLinks);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Photos3.this, "Error Loading Images", Toast.LENGTH_SHORT).show();
            }

        });


    }
    private void showPopupMenu(BottomNavigationView bottomNavView) {
        PopupMenu popupMenu = new PopupMenu(this, bottomNavView, Gravity.END);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        // Set up a listener for the items in the popup menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();


                if (itemId == R.id.nav_Donations)
                {

                    return true;
                }
                if (itemId == R.id.nav_Join_Us)
                {
                    Intent intent = new Intent(Photos3.this, JoinMinistryActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Appointments)
                {
                    Intent intent = new Intent(Photos3.this, AppointmentsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Log_Out)
                {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    return true;
                }
                if (itemId == R.id.nav_Comm_Chat)
                {
                    return true;
                }
                if (itemId == R.id.nav_Photos)
                {
                    return true;
                }
                if (itemId == R.id.nav_Volunteer)
                {
                    return true;
                }
                if (itemId == R.id.nav_About)
                {
                    Intent intent = new Intent(Photos3.this, About1.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Account)
                {
                    Intent intent = new Intent(Photos3.this, Photos1.class);
                    startActivity(intent);
                    return true;
                }



                return false;
            }
        });
        popupMenu.show();
    }
}