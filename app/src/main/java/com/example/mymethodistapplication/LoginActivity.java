package com.example.mymethodistapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymethodistapplication.databinding.ActivityLoginBinding;
import com.example.mymethodistapplication.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
private EditText LoginEmailAddress2;
private EditText  LoginPassword;
private ProgressBar progressBar;
private FirebaseAuth authProfile;

    Button CompleteLoginButton;
    Button YouTubeButton;
    ImageView imageView2;
    Button FaceBookButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clickhere = (TextView) findViewById(R.id.clickheretext);

       // getSupportActionBar().setTitle("Login");
        LoginEmailAddress2 =findViewById(R.id.editTextTextEmailAddress2);
        LoginPassword = findViewById(R.id.editTextTextPassword);
        progressBar = findViewById(R.id.ProgressBar);
        CompleteLoginButton = findViewById(R.id.CompleteLoginButton);
        authProfile = FirebaseAuth.getInstance();

        YouTubeButton =(Button) findViewById(R.id.YouTubeButton);
        YouTubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@gracewellmethodistchurch/videos")));

            }
        });

        FaceBookButton =(Button) findViewById(R.id.FacebookButton);
        FaceBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/GraceWellMC/")));

            }
        });


        clickhere = (TextView) findViewById(R.id.clickheretext);
        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openregister();
            }
        });

        CompleteLoginButton.setOnClickListener(new View.OnClickListener() {

            @Override //Activity To A Fragment
            public void onClick(View view) {

                String editTextTextPassword = LoginPassword.getText().toString();
                String editTextEmailAddress2 = LoginEmailAddress2.getText().toString();



                if(TextUtils.isEmpty(editTextEmailAddress2)){
                    Toast.makeText(LoginActivity.this,"Please Enter Your Email Address",Toast.LENGTH_SHORT).show();
                    LoginEmailAddress2.setError("Email Address Is Required");
                    LoginEmailAddress2.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(editTextEmailAddress2).matches()){
                    Toast.makeText(LoginActivity.this,"Please Enter Your Email Address",Toast.LENGTH_SHORT).show();
                    LoginEmailAddress2.setError("Please Enter A Valid Email Address");
                    LoginEmailAddress2.requestFocus();
                }
                else if(TextUtils.isEmpty(editTextTextPassword)){
                    Toast.makeText(LoginActivity.this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
                    LoginPassword.setError("Password Is Required");
                    LoginPassword.requestFocus();
                }

                else {
                    progressBar.setVisibility(view.VISIBLE);
                    LoginUser(editTextEmailAddress2, editTextTextPassword);
                }
                    if (editTextEmailAddress2.equals("aletta.meyer707@gmail.com")&& editTextTextPassword.equals("Gracewell123")) {
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    overridePendingTransition(0, 0);
                }


                /*

                Fragment HomePage = new HomePage();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.LoginActivity, HomePage).commit();

                 */

            }


        });

// Start of showing image from database
        // getting ImageView by its id
        imageView2 = findViewById(R.id.imageView2);

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
                = databaseReference.child("churchlogo");

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
                        Picasso.get().load(link).into(imageView2);
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
                                .makeText(LoginActivity.this,
                                        "Error Loading Image",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        // End of showing image from database
    }
    private TextView clickhere;

    private void openregister() {
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }
    private void LoginUser(String email, String password) {

        authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK|
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    /*
                    Fragment HomePage = new HomePage();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.LoginActivity, HomePage).commit();
                    //Toast.makeText(LoginActivity.this,"You Are Logged in",Toast.LENGTH_SHORT).show();
*/
                }

                else {
                    Toast.makeText(LoginActivity.this,"Something Has Gone Wrong",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }


}
