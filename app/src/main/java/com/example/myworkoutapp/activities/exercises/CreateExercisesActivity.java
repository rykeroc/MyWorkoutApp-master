package com.example.myworkoutapp.activities.exercises;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.app.info.ExerciseInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CreateExercisesActivity extends AppCompatActivity {

    private EditText exerciseNameEditText;
    private EditText descriptionEditTExt;
    private NumberPicker setsNumberPicker;
    private NumberPicker repsNumberPicker;
    private Button createButton;

    String userID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercises);

        //Get Firebase User ID
        userID = FirebaseAuth.getInstance().getUid();

        //Set Edit Texts
        exerciseNameEditText = findViewById(R.id.cepuExerciseNameInput);
        descriptionEditTExt = findViewById(R.id.cepuDescriptionInput);

        //Set Number Pickers
        setsNumberPicker = findViewById(R.id.cepuSetsNumberPicker);
        setNumberPickerMinMax(setsNumberPicker);

        repsNumberPicker = findViewById(R.id.cepuRepsNumberPicker);
        setNumberPickerMinMax(repsNumberPicker);

        //Set save Button
        createButton = findViewById(R.id.cepuSaveExerciseButton);

        //Save button on click listener
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createClick(view);
            }
        });
    }

    //Method to set NumberPicker min to 1 and max to 10
    private void setNumberPickerMinMax(NumberPicker numberPicker){
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
    }

    private void createClick(View view) {
        //Unique ID for exercise
        String exerciseID = UUID.randomUUID().toString();
        String exerciseName = exerciseNameEditText.getText().toString().trim();
        String description = descriptionEditTExt.getText().toString().trim();
        String recommendedSets = String.valueOf(setsNumberPicker.getValue());
        String recommendedReps = String.valueOf(repsNumberPicker.getValue());

        //Verify user inputs for exercise name and description
        if (!(verifyInputs(exerciseName, description)))
            return;

        //Set path to
        String path = String.format("exercises/%s/%s", userID, exerciseID);

        ExerciseInfo newExercise = new ExerciseInfo(exerciseID, exerciseName, description,
                recommendedReps, recommendedSets);

        //Add new exercise to Firebase database
        FirebaseDatabase.getInstance().getReference().child(path).setValue(newExercise);
        Toast.makeText(this, "Exercise created", Toast.LENGTH_SHORT).show();
        CreateExercisesActivity.super.finish();
    }

    private boolean verifyInputs(String exerciseName, String description) {
        if(exerciseName.isEmpty()){
            this.exerciseNameEditText.setError("Exercise name is required!");
            this.exerciseNameEditText.requestFocus();
            return false;
        }

        if(description.isEmpty()){
            this.descriptionEditTExt.setError("Exercise description is required!");
            this.descriptionEditTExt.requestFocus();
            return false;
        }
        return true;
    }
}