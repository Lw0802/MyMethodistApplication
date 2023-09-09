package com.example.mymethodistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Photos1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos1);

        Y2023 = (TextView) findViewById(R.id.Y2023);
        Y2023.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choice = 0;

                Year2023(choice);
            }
        });

        Y2022 = (TextView) findViewById(R.id.Y2022);
        Y2022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choice = 1;
                Year2022(choice);
            }
        });
    }
    private TextView Y2023;
    private TextView Y2022;

    public int choice;


    private void Year2023(int choice) {
        Intent intent = new Intent(this, Photos3.class);
        intent.putExtra("choiceKey", this.choice);
        startActivity(intent);
    }
    private void Year2022(int choice) {
        Intent intent = new Intent(this, Photos3.class);
        intent.putExtra("choiceKey", this.choice);
        startActivity(intent);
    }
}