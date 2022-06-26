package com.example.myworkoutapp.activities.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.activities.mainmenu.MainMenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button signInButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        signInButton = findViewById(R.id.siSignInButton);
        emailEditText = findViewById(R.id.siEmailInput);
        passwordEditText = findViewById(R.id.siPasswordInput);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    public void signIn(){
        Context context = this;
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        verifyCredentials(email,password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Sign-In Successful", Toast.LENGTH_SHORT).show();
                            saveToSharedPref(email, password);
                            startActivity(new Intent(context, MainMenuActivity.class));
                        } else
                            Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void verifyCredentials(String email, String password) {
        if (email.isEmpty()){
            emailEditText.setError("Enter email address");
            emailEditText.requestFocus();
            return;
        }
        if (password.isEmpty()){
            passwordEditText.setError("Enter password");
            passwordEditText.requestFocus();
        }
    }

    private void saveToSharedPref(String email, String password){
        SharedPreferences sharedpref = getSharedPreferences("MyWorkoutApp.SharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();

        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }
}