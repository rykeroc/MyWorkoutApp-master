package com.example.myworkoutapp.activities.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.activities.logs.LogsActivity;
import com.example.myworkoutapp.activities.exercises.ExercisesActivity;
import com.example.myworkoutapp.activities.workouts.WorkoutActivity;

public class MainMenuActivity extends AppCompatActivity {

    private Button exerciseButton;
    private Button workoutsButton;
    private Button logsButton;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Sets all Views in Activity
        setViews();

        //Sets all onClickListeners in Activity
        setOnClickListeners();
    }

    //Sets all Views in Activity
    public void setViews(){
        exerciseButton = findViewById(R.id.mmExercisesButton);
        workoutsButton = findViewById(R.id.mmWorkoutsButton);
        logsButton = findViewById(R.id.mmLogsButton);
        settingsButton = findViewById(R.id.mmSettingsButton);
    }

    //Sets all onClickListeners in Activity
    public void setOnClickListeners(){
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exercisesClick(view);
            }
        });

        workoutsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workoutsClick(view);
            }
        });

        logsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logsClick(view);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClick(view);
            }
        });
    }

    //Launch LogsActivity
    private void logsClick(View view) {
        startActivity(new Intent(this, LogsActivity.class));
    }

    //Launch ExercisesActivity
    private void exercisesClick(View view){
        startActivity(new Intent(this, ExercisesActivity.class));
    }

    //Launch WorkoutActivity
    private void workoutsClick(View view){
        startActivity(new Intent(this, WorkoutActivity.class));
    }

    //Launch SettingsMenuActivity
    private void settingsClick(View view){
        startActivity(new Intent(this, SettingsMenuActivity.class));
    }
}