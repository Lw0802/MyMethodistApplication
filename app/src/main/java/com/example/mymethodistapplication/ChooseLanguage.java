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
        Button afrikaansbutton = (Button) findViewById(R.id.afrikaansbutton);
        afrikaansbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang.updateResource("af");
                recreate();
            }
        });

        Button englishbutton = (Button) findViewById(R.id.englishbutton);
        englishbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang.updateResource("en");
                recreate();
            }
        });

        Button hindibutton = (Button) findViewById(R.id.hindibutton);
        hindibutton.setOnClickListener(new View.OnClickListener() {
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

        // Start of showing image from database
        // getting ImageView by its id
        ImageView englishflag = findViewById(R.id.englishflag);


        // we will get the default FirebaseDatabase instance
        FirebaseDatabase firebaseDatabase
                = FirebaseDatabase.getInstance();

        // we will get a DatabaseReference for the database
        // root node
        DatabaseReference databaseReference
                = firebaseDatabase.getReference();

        // Here "image" is the child node value we are
        // getting child node data in the getImage variable
        DatabaseReference getImage
                = databaseReference.child("englishlang");

        // Adding listener for a single change
        // in the data at this location.
        // this listener will triggered once
        // with the value of the data at the location
        getImage.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(
                            @NonNull DataSnapshot dataSnapshot) {
                        // getting a DataSnapshot for the
                        // location at the specified relative
                        // path and getting in the link variable
                        String link = dataSnapshot.getValue(
                                String.class);

                        // loading that data into rImage
                        // variable which is ImageView
                        Picasso.get().load(link).into(englishflag);
                    }

                    // this will called when any problem
                    // occurs in getting data
                    @Override
                    public void onCancelled(
                            @NonNull DatabaseError databaseError)
                    {
                        // we are showing that error message in
                        // toast
                        Toast
                                .makeText(ChooseLanguage.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        // End of showing image from database
    }

    private void showPopupMenu(BottomNavigationView bottomNavView) {
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
