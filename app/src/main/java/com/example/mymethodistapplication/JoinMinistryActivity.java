package com.example.mymethodistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.mymethodistapplication.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class JoinMinistryActivity extends AppCompatActivity {
    JoinMinistryActivity binding;
    BottomNavigationView bottomNavigationView;
    Button FaceBookVerseButton;

    Button FaceBookButton;

    Button YouTubeButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_ministry);
      // binding = JoinMinistryActivity.inflate(getLayoutInflater());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home_nav);
        bottomNavigationView.setLabelVisibilityMode(bottomNavigationView.LABEL_VISIBILITY_LABELED);



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
                    Intent intent = new Intent(JoinMinistryActivity.this, JoinMinistryActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Appointments)
                {
                    Intent intent = new Intent(JoinMinistryActivity.this, AppointmentsActivity.class);
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
                    Intent intent = new Intent(JoinMinistryActivity.this, About1.class);
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
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frame_layout);
        fragmentTransaction.commit();
    }
}