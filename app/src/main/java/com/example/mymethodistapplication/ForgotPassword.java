package com.example.mymethodistapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymethodistapplication.databinding.ActivityAbout1Binding;
import com.example.mymethodistapplication.databinding.ActivityForgotPasswordBinding;
import com.example.mymethodistapplication.databinding.ActivityUpdateuserinfoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ForgotPassword extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ActivityForgotPasswordBinding binding;

    private EditText newEmailEditText;
    private EditText passwordEditText;
    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private TextView testtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);



    }


    public void resetPassword(View view) {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgotPassword.this, "Failed to send reset email. Please check your email address.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}


