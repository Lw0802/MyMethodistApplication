package com.example.mymethodistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        changepassword = (TextView) findViewById(R.id.changepassword);
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openchangepassword();
            }
        });

        changelanguage = (TextView) findViewById(R.id.changelanguage);
        changelanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openchangelanguage();
            }
        });
    }

    private TextView changepassword;
    private TextView changelanguage;

    private void openchangepassword() {
        Intent intent = new Intent(this, changepassword.class);
        startActivity(intent);
    }
    private void openchangelanguage() {
        Intent intent = new Intent(this, ChooseLanguage.class);
        startActivity(intent);
    }
}