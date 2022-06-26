package com.example.myworkoutapp.activities.exercises;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.app.info.ActivityInfo;
import com.example.myworkoutapp.app.info.ExerciseInfo;
import com.example.myworkoutapp.listeners.ExerciseChildListener;
import com.example.myworkoutapp.listviews.CheckableViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//Activity used to view all user Exercises
public class ExercisesActivity extends AppCompatActivity {

    private DatabaseReference exercisesRef;         //ref to exercises/*UID*

    private ImageButton addExercisesButton;              //Button to add a new exercise
    private ImageButton homeButton;                 //Button to return to home screen
    private RecyclerView recyView;           //Layout for new exercise buttons
    private CheckableViewAdapter exAdapter;

    private ArrayList<ActivityInfo> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        //Set UID
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //Set database ref with current UID
        this.exercisesRef = FirebaseDatabase.getInstance().getReference("exercises/" + currentUser);

        setViews();

        this.exercises = new ArrayList<>();

        //Set on click listeners for addExerciseButton and homeButton
        setOnClick();

        exAdapter = new CheckableViewAdapter(ExercisesActivity.this, ExercisesActivity.this.exercises, 2);
        this.recyView.setAdapter(exAdapter);
        this.recyView.setLayoutManager(new LinearLayoutManager(this));

        //Set exercise child listener
        this.exercisesRef.addChildEventListener(new ExerciseChildListener(exercises, exAdapter));


        //TODO: on reload click remove recycler view and re add
    }

    private void setViews() {
        //Link buttons
        addExercisesButton = findViewById(R.id.eaAddExercise);
        homeButton = findViewById(R.id.eaExerciseHomeButton);

        //Link layout for new exercise buttons
        recyView = findViewById(R.id.exRecyView);
    }


    //Set on click listeners in activity_exercises
    private void setOnClick(){
        //Sets add exercise button
        addExercisesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExercisesActivity.this, CreateExercisesActivity.class));
            }
        });

        //Sets home button click
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExercisesActivity.super.finish();
            }
        });
    }
}