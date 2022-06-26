package com.example.myworkoutapp.activities.mainscreen;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myworkoutapp.R;
import com.example.myworkoutapp.activities.mainmenu.MainMenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TitleActivity extends AppCompatActivity {
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
        Context context = this;

        startActivity(new Intent(this, MainMenuActivity.class));

        getSavedPref();

        if((this.email != null) && (this.password != null)){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(this.email, this.password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(context, MainMenuActivity.class));
                    }
                    else {
                        startActivity(new Intent(context, MainScreenActivity.class));
                    }
                }
            });
        } else
            startActivity(new Intent(this, MainScreenActivity.class));
    }

    private void getSavedPref(){
        SharedPreferences sharedPref;
        if(getSharedPreferences("MyWorkoutApp.SharedPreferences", MODE_PRIVATE) != null) {
            sharedPref = getSharedPreferences("MyWorkoutApp.SharedPreferences", MODE_PRIVATE);
                this.email = sharedPref.getString("email", " ");
                this.password = sharedPref.getString("password", " ");
        }
    }
}