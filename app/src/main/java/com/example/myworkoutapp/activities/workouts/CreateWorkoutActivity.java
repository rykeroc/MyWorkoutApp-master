package com.example.myworkoutapp.activities.workouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.activities.exercises.ExercisesActivity;
import com.example.myworkoutapp.app.info.ActivityInfo;
import com.example.myworkoutapp.listeners.ExerciseChildListener;
import com.example.myworkoutapp.listviews.CheckableViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateWorkoutActivity extends AppCompatActivity {
    private EditText workoutNameEditText;
    private EditText descriptionEditText;
    private Button createButton;
    private RecyclerView exercisesList;
    private CheckableViewAdapter adapter;

    private ArrayList<ActivityInfo> existingExercises;

    private DatabaseReference userExercises;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        this.userID = FirebaseAuth.getInstance().getUid();
        this.userExercises = FirebaseDatabase.getInstance().getReference("exercises/" + this.userID);

        //Set all Views in Activity
        setViews();

        this.existingExercises = new ArrayList<>();

        //Set OnClickListener
        setOnClick();

        adapter = new CheckableViewAdapter(CreateWorkoutActivity.this, CreateWorkoutActivity.this.existingExercises, 1);
        this.exercisesList.setAdapter(adapter);
        this.exercisesList.setLayoutManager(new LinearLayoutManager(this));

        this.userExercises.addChildEventListener(new ExerciseChildListener(existingExercises, adapter));
    }



    //Set all Views in Activity
    private void setViews() {
        this.workoutNameEditText = findViewById(R.id.cwExerciseNameInput);
        this.descriptionEditText = findViewById(R.id.cwDescriptionInput);
        this.createButton = findViewById(R.id.cwCreateButton);
        this.exercisesList = findViewById(R.id.cwRecyclerView);
    }

    //Set all OnClickListeners
    private void setOnClick() {
        this.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createWorkoutClick();
            }
        });
    }

    //TODO
    private void createWorkoutClick() {
    }

}