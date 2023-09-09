package com.example.mymethodistapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.mymethodistapplication.databinding.ActivityAbout1Binding;
import com.example.mymethodistapplication.databinding.ActivityChangeLanguageBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ChooseLanguage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActivityChangeLanguageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        binding = ActivityChangeLanguageBinding.inflate(getLayoutInflater());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(bottomNavigationView.LABEL_VISIBILITY_LABELED);

        LanguageManager lang = new LanguageManager(this);

        TextView afrikaanstext  = findViewById(R.id.afrikaanstext);
        afrikaanstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang.updateResource("af");
                recreate();
            }
        });


        TextView englishtext  = findViewById(R.id.englishtext);
        englishtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang.updateResource("en");
                recreate();
            }
        });


        TextView zulustext  = findViewById(R.id.zulutext);
        zulustext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang.updateResource("hi");
                recreate();
            }
        });
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.sermons_nav) {
                startActivity(new Intent(getApplicationContext(), SermonsPage.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.events_nav) {
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.contactus_nav) {
                startActivity(new Intent(getApplicationContext(), ContactUs.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (item.getItemId() == R.id.menu_nav) {

                showPopupMenu(bottomNavigationView);

                return true;
            } else if (item.getItemId() == R.id.home_nav) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;

            }


            return false;
        });




        // Define an array of ImageViews
        ImageView[] imageViews = new ImageView[4]; // Replace 3 with the number of ImageViews you have

// Assign the ImageViews by their unique IDs
        imageViews[0] = findViewById(R.id.lang1); // Replace with your ImageView IDs
        imageViews[1] = findViewById(R.id.lang2);
        imageViews[2] = findViewById(R.id.lang3);
        imageViews[3] = findViewById(R.id.lang4);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference imagesReference = databaseReference.child("Images");

        imagesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int index = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String link = snapshot.getValue(String.class);

                    // Check if the index is within the range of your ImageViews array
                    if (index < imageViews.length) {
                        // Load the image into the corresponding ImageView
                        Picasso.get().load(link).into(imageViews[index]);
                        index++;
                    } else {
                        // Handle the case where you have more image URLs than ImageViews
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    private void showPopupMenu (BottomNavigationView bottomNavView){
        PopupMenu popupMenu = new PopupMenu(this, bottomNavView, Gravity.END);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        // Set up a listener for the items in the popup menu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();


                if (itemId == R.id.nav_Donations) {
                    return true;
                }
                if (itemId == R.id.nav_Join_Us) {
                    Intent intent = new Intent(ChooseLanguage.this, JoinMinistryActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Appointments) {
                    Intent intent = new Intent(ChooseLanguage.this, AppointmentsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Log_Out) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    return true;
                }
                if (itemId == R.id.nav_Comm_Chat) {
                    return true;
                }
                if (itemId == R.id.nav_Photos) {
                    return true;
                }
                if (itemId == R.id.nav_Volunteer) {
                    return true;
                }
                if (itemId == R.id.nav_About) {
                    Intent intent = new Intent(ChooseLanguage.this, ChooseLanguage.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Account) {
                    return true;
                }


                return false;
            }
        });
        popupMenu.show();
    }
}