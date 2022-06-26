package com.example.myworkoutapp.app.logs;

import java.util.ArrayList;

public class Workout {
    private String workoutName;
    private ArrayList <Exercise> exercises;

    public Workout(String workoutName, ArrayList<Exercise> exercises) {
        this.workoutName = workoutName;
        this.exercises = exercises;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }
}
