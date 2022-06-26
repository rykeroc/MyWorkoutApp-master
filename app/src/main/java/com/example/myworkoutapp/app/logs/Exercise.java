package com.example.myworkoutapp.app.logs;

import java.util.ArrayList;

public class Exercise {
    private String exerciseName;
    private ArrayList<ExerciseSet> sets;

    public Exercise(String exerciseName, ArrayList<ExerciseSet> sets) {
        this.exerciseName = exerciseName;
        this.sets = sets;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public ArrayList<ExerciseSet> getSets() {
        return sets;
    }

    public void setSets(ArrayList<ExerciseSet> sets) {
        this.sets = sets;
    }
}
