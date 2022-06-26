package com.example.myworkoutapp.activities.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myworkoutapp.R;

public class MainScreenActivity extends AppCompatActivity {

    private Button registerButton;
    private Button signInButton;
    private Button forgoPasswordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        registerButton = findViewById(R.id.msRegisterButton);
        signInButton = findViewById(R.id.msSignInButton);
        forgoPasswordButton = findViewById(R.id.msForgotPassword);

        setOnClickListeners();
        }

    private void setOnClickListeners(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegisterAccountActivity();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIActivity();
            }
        });

        forgoPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForgotPassActivity();
            }
        });

    }

    private void startForgotPassActivity() {
        //TODO: Implement forgot password
    }

    private void startRegisterAccountActivity() {
        startActivity(new Intent(this, RegisterAccountActivity.class));
    }

    private void startSignIActivity() {
        startActivity(new Intent(this, SignInActivity.class));
    }


}