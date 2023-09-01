package com.example.mymethodistapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymethodistapplication.databinding.ActivityEventsBinding;
import com.example.mymethodistapplication.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class EventsActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{

 ActivityEventsBinding binding;

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    BottomNavigationView bottomNavigationView;


    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        binding = ActivityEventsBinding.inflate(getLayoutInflater());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.events_nav);
        bottomNavigationView.setLabelVisibilityMode(bottomNavigationView.LABEL_VISIBILITY_LABELED);

        intWidgets();

        selectedDate = LocalDate.now();
        setMonthView();




        bottomNavigationView.setOnItemSelectedListener(item ->   {

            if(item.getItemId() ==R.id.sermons_nav){
                startActivity(new Intent(getApplicationContext(),SermonsPage.class));
                overridePendingTransition(0,0);
                return true;
            }
            else if (item.getItemId()==R.id.events_nav) {
                startActivity(new Intent(getApplicationContext(),EventsActivity.class));
                overridePendingTransition(0,0);
                return true;
            }
            else if (item.getItemId()==R.id.contactus_nav) {
                startActivity(new Intent(getApplicationContext(),ContactUs.class));
                overridePendingTransition(0,0);
                return true;
            }
            else if (item.getItemId()==R.id.menu_nav) {
                showPopupMenu(bottomNavigationView);
                return true;
            }
            else if (item.getItemId()==R.id.home_nav) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                overridePendingTransition(0,0);
                return true;

            }


            return false;
        });
    }



    private void intWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }

    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = null;
        yearMonth = YearMonth.from(date);
        int daysInMonth = 0;
        daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = null;
        firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = 0;
        dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for (int i = 1; i<=42; i++)
        {
            if (i <=dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
            }
            else
            {
                daysInMonthArray.add(String.valueOf(i -dayOfWeek));

            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate date){

        DateTimeFormatter formatter = null;
        formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }


    public void previousMonthAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();

    }
    public void nextMonthAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();

    }


    @Override
    public void OnItemClick(int position, String dayText) {
        if(dayText.equals(""))
        {
            String message = "Selected  Date " + dayText +"" +  monthYearFromDate(selectedDate);
            Toast.makeText(this, ""+dayText, Toast.LENGTH_LONG).show();
        }

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
                    Intent intent = new Intent(EventsActivity.this, JoinMinistryActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (itemId == R.id.nav_Appointments)
                {
                    Intent intent = new Intent(EventsActivity.this, AppointmentsActivity.class);
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
                    Intent intent = new Intent(EventsActivity.this, About1.class);
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