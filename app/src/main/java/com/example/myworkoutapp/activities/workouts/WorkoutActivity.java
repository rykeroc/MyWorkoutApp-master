package com.example.myworkoutapp.activities.workouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.app.info.ActivityInfo;
import com.example.myworkoutapp.listeners.WorkoutChildListener;
import com.example.myworkoutapp.listviews.CheckableViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WorkoutActivity extends AppCompatActivity {
    private DatabaseReference workoutsRef;

    private ImageButton addWorkoutButton;
    private ImageButton homeButton;
    private RecyclerView recyView;
    private CheckableViewAdapter woAdapter;

    private ArrayList<ActivityInfo> workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        //Set UID
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //Set database ref with current UID
        this.workoutsRef = FirebaseDatabase.getInstance().getReference("workouts/" + currentUser);

        //Set all Views
        setViews();

        this.workouts = new ArrayList<>();

        //Set all onClickListeners
        setOnClick();

        woAdapter = new CheckableViewAdapter(WorkoutActivity.this, WorkoutActivity.this.workouts, 2);
        this.recyView.setAdapter(woAdapter);
        this.recyView.setLayoutManager(new LinearLayoutManager(this));

        this.workoutsRef.addChildEventListener(new WorkoutChildListener(workouts, woAdapter));
    }

    //Set all Views
    private void setViews(){
        addWorkoutButton = findViewById(R.id.wAddWorkout);
        homeButton = findViewById(R.id.wWorkoutHomeButton);
        recyView = findViewById(R.id.wRecyclerView);
    }

    //Set all onClickListeners
    private void setOnClick(){
        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWorkout();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkoutActivity.super.finish();
            }
        });
    }

    //Launch CreateWorkoutActivity
    private void addWorkout() {
        startActivity(new Intent(WorkoutActivity.this, CreateWorkoutActivity.class));
    }
}