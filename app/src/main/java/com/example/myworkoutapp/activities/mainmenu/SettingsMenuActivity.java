package com.example.myworkoutapp.activities.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.activities.mainscreen.MainScreenActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsMenuActivity extends AppCompatActivity {

    Button signOutButton;
    ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_menu);

        signOutButton = findViewById(R.id.smSignOutButton);
        homeButton = findViewById(R.id.smSettingsHomeButton);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsMenuActivity.super.finish();
            }
        });
    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        clearSharedPreferences();
        startActivity(new Intent(this, MainScreenActivity.class));
    }

    private void clearSharedPreferences(){
        SharedPreferences sharedPref = getSharedPreferences("MyWorkoutApp.SharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.clear().apply();
    }
}