package com.example.myworkoutapp.app.info;

import java.util.ArrayList;
import java.util.HashMap;

public class WorkoutInfo extends ActivityInfo {
    private ArrayList<String> exerciseIds;

    public static final String woID = "workoutID";
    public static final String woName = "workoutName";
    public static final String woDesc = "workoutDescription";
    public static final String woExercises = "exercises";


    public WorkoutInfo(String id, String workoutName, String workoutDescription, ArrayList<String> exercises)
    {
        super(id, workoutName, workoutDescription);
        this.exerciseIds = exercises;
    }

    public ArrayList<String> getExercises() {
        return exerciseIds;
    }

    public void setExercises(ArrayList<String> exercises) {
        this.exerciseIds = exercises;
    }
}
