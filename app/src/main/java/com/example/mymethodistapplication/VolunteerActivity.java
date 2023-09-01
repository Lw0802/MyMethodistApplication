package com.example.mymethodistapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.mymethodistapplication.databinding.ActivityVolunteerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class VolunteerActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    ActivityVolunteerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        if (findViewById(R.id.main_activity) != null) {
            if (savedInstanceState != null) {
                return;
            }

        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_LABELED);


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
                        Intent intent = new Intent(VolunteerActivity.this, JoinMinistryActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    if (itemId == R.id.nav_Appointments)
                    {
                        Intent intent = new Intent(VolunteerActivity.this, AppointmentsActivity.class);
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
                        Intent intent = new Intent(VolunteerActivity.this, About1.class);
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
    }

