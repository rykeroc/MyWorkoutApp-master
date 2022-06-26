package com.example.myworkoutapp.app.logs;

public class ExerciseSet {
    private int setNumber;
    private int numOfReps;
    private float weightUsed;

    public ExerciseSet(int setNumber, int numOfReps, float weightUsed) {
        this.setNumber = setNumber;
        this.numOfReps = numOfReps;
        this.weightUsed = weightUsed;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getNumOfReps() {
        return numOfReps;
    }

    public void setNumOfReps(int numOfReps) {
        this.numOfReps = numOfReps;
    }

    public float getWeightUsed() {
        return weightUsed;
    }

    public void setWeightUsed(float weightUsed) {
        this.weightUsed = weightUsed;
    }

}
