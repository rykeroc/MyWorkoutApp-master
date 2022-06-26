package com.example.myworkoutapp.listeners;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myworkoutapp.app.info.ActivityInfo;
import com.example.myworkoutapp.app.info.ExerciseInfo;
import com.example.myworkoutapp.app.info.WorkoutInfo;
import com.example.myworkoutapp.listviews.CheckableViewAdapter;
import com.example.myworkoutapp.listviews.CheckableViewHandler;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class WorkoutChildListener implements ChildEventListener {
    private final CheckableViewAdapter workoutsAdpater;
    private final ArrayList<ActivityInfo> workoutsList;

    public WorkoutChildListener(ArrayList<ActivityInfo> workoutsList, CheckableViewAdapter workoutsAdpater){
        this.workoutsAdpater = workoutsAdpater;
        this.workoutsList = workoutsList;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        ArrayList<String> exercisesInWorkout;

        //Adding new child to Workouts/Uid
        String woID = snapshot.getKey();
        String woName = snapshot.child(ExerciseInfo.exName).getValue(String.class);
        String woDecript = snapshot.child(ExerciseInfo.exDescription).getValue(String.class);
//        //TODO uncomment when create exercise is made
//        WorkoutInfo newWorkout = new WorkoutInfo(woID, woName, woDecript, exercisesInWorkout);
//        this.workoutsList.add(newWorkout);
//
//        this.workoutsAdpater.notifyItemInserted(this.workoutsList.indexOf(newWorkout));
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        String idOfChanged = snapshot.getKey();
        int exIndex = -1;
        WorkoutInfo changedWorkout = null;

        //Get index of workout that was changed
        for(ActivityInfo workout : this.workoutsList){
            if(idOfChanged.equals(workout.getId())){
                exIndex = this.workoutsList.indexOf(workout);
                changedWorkout = (WorkoutInfo) workout;
            }
        }

        if(changedWorkout == null)
            return;
        //TODO print error occurred

        //If workout name in ArrayList does not equal firebase entry, update name in ArrayList
        if(!changedWorkout.getName().equals(snapshot.child(WorkoutInfo.woName).getValue(String.class)))
            changedWorkout.setName(snapshot.child(WorkoutInfo.woName).getValue(String.class));

        //If workout name in ArrayList does not equal firebase entry, update name in ArrayList
        if(!changedWorkout.getDescription().equals(snapshot.child(WorkoutInfo.woDesc).getValue(String.class)))
            changedWorkout.setDescription(snapshot.child(WorkoutInfo.woDesc).getValue(String.class));

        /*
        TODO:
            Add check for change in exercises in workout
            Update exercises in workout
         */
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
        String idOfRemoved = snapshot.getKey();
        int exIndex = -1;

        //Get index of workout that was changed
        for(ActivityInfo workout : this.workoutsList)
            if(idOfRemoved.equals(workout.getId()))
                exIndex = this.workoutsList.indexOf(workout);

        this.workoutsList.remove(exIndex);

        this.workoutsAdpater.notifyItemRemoved(exIndex);
    }

    //TODO
    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    //TODO
    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
