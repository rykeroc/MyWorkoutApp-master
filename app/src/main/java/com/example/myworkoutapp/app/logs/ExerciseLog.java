package com.example.myworkoutapp.app.logs;

import java.util.ArrayList;

public class ExerciseLog {
    private final String LOG_USER;
    private final long LOG_ID;
    private ArrayList<Exercise> exercises;

    public ExerciseLog(String LOG_USER, long LOG_ID, ArrayList<Exercise> exercises) {
        this.LOG_USER = LOG_USER;
        this.LOG_ID = LOG_ID;
        this.exercises = exercises;
    }

    public String getLOG_USER() {
        return LOG_USER;
    }

    public long getLOG_ID() {
        return LOG_ID;
    }
}
