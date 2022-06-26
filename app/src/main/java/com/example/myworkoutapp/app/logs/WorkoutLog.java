package com.example.myworkoutapp.app.logs;

import com.example.myworkoutapp.app.info.WorkoutInfo;

public class WorkoutLog {
    private final String LOG_USER;
    private final long LOG_ID;
    private final Workout workout;

    public WorkoutLog(String LOG_USER, long LOG_ID, Workout workout) {
        this.LOG_USER = LOG_USER;
        this.LOG_ID = LOG_ID;
        this.workout = workout;
    }

    public String getLOG_USER() {
        return LOG_USER;
    }

    public long getLOG_ID() {
        return LOG_ID;
    }

    public Workout getWorkout() {
        return workout;
    }
}
