package com.example.mymethodistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.mymethodistapplication.databinding.ActivityAbout1Binding;
import com.example.mymethodistapplication.databinding.ActivityAbout2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class About2 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActivityAbout2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);
        binding = ActivityAbout2Binding.inflate(getLayoutInflater());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(bottomNavigationView.LABEL_VISIBILITY_LABELED);

        historytext = (TextView) findViewById(R.id.historytext);
        historytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout1();
            }
        });

        missiontext = (TextView) findViewById(R.id.missiontext);
        missiontext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout2();
            }
        });

        beliefstext = (TextView) findViewById(R.id.beliefstext);
        beliefstext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout3();
            }
        });

        faithtext = (TextView) findViewById(R.id.faithtext);
        faithtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout4();
            }
        });

        stafftext = (TextView) findViewById(R.id.stafftext);
        stafftext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout5();
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
            }
            else if (item.getItemId() == R.id.home_nav) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                overridePendingTransition(0, 0);
                return true;

            }


            return false;
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
                    Intent intent = new Intent(About2.this, JoinMinistryActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Appointments)
                {
                    Intent intent = new Intent(About2.this, AppointmentsActivity.class);
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
                    Intent intent = new Intent(About2.this, About1.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Account)
                {
                    return true;
                }



                return false;
            }
        });
        popupMenu.show();
    }


    private TextView historytext;
    private TextView missiontext;
    private TextView beliefstext;
    private TextView faithtext;
    private TextView stafftext;

    private void openAbout1() {
        Intent intent = new Intent(this, About1.class);
        startActivity(intent);
    }
    private void openAbout2() {
        Intent intent = new Intent(this, About2.class);
        startActivity(intent);
    }
    private void openAbout3() {
        Intent intent = new Intent(this, About3.class);
        startActivity(intent);
    }
    private void openAbout4() {
        Intent intent = new Intent(this, About4.class);
        startActivity(intent);
    }
    private void openAbout5() {
        Intent intent = new Intent(this, About5.class);
        startActivity(intent);
    }

}