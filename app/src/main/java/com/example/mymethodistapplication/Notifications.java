package com.example.mymethodistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Notifications extends AppCompatActivity {

    private CheckBox emailNotificationsCheckbox;
    private CheckBox inAppNotificationsCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        emailNotificationsCheckbox = findViewById(R.id.emailNotificationsCheckbox);
        inAppNotificationsCheckbox = findViewById(R.id.inAppNotificationsCheckbox);

        // Load user preferences and set the checkbox states accordingly
        // You can retrieve these preferences from SharedPreferences or any other storage method.
        // For example, if SharedPreferences is used:

        boolean receiveEmailNotifications = false; // Load from SharedPreferences
        boolean receiveInAppNotifications = false; // Load from SharedPreferences


                emailNotificationsCheckbox.setChecked(receiveEmailNotifications);
        inAppNotificationsCheckbox.setChecked(receiveInAppNotifications);
    }

    public void saveNotificationPreferences(View view) {
        boolean receiveEmailNotifications = emailNotificationsCheckbox.isChecked();
        boolean receiveInAppNotifications = inAppNotificationsCheckbox.isChecked();

        // Save these preferences, e.g., to SharedPreferences or your server
        // Update the user's notification preferences as needed

        Toast.makeText(this, "Notification preferences saved", Toast.LENGTH_SHORT).show();

    }
}