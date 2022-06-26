package com.example.myworkoutapp.listeners;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myworkoutapp.app.info.ActivityInfo;
import com.example.myworkoutapp.app.info.ExerciseInfo;
import com.example.myworkoutapp.listviews.CheckableViewAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class ExerciseChildListener implements ChildEventListener {
    private final ArrayList<ActivityInfo> exerciseList;
    private final CheckableViewAdapter adapter;

    public ExerciseChildListener(ArrayList<ActivityInfo> exercises, CheckableViewAdapter adapter){
        this.exerciseList = exercises;
        this.adapter = adapter;
    }

    //Add buttons for exercises everytime a child is added to exercises/userid
    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        //Adding new child to Exercises/Uid
        String exID = snapshot.getKey();
        String exName = snapshot.child(ExerciseInfo.exName).getValue(String.class);
        String exDecript = snapshot.child(ExerciseInfo.exDescription).getValue(String.class);
        String exRecReps = snapshot.child(ExerciseInfo.exRecReps).getValue(String.class);
        String exRecSets = snapshot.child(ExerciseInfo.exRecSets).getValue(String.class);
        String maxWeight = snapshot.child(ExerciseInfo.exMaxWeight).getValue(String.class);

        ExerciseInfo newExercise = new ExerciseInfo(exID, exName, exDecript, exRecReps, exRecSets, maxWeight);
        this.exerciseList.add(newExercise);

        //Notify Recycler adapter of new item
        this.adapter.notifyItemInserted(this.exerciseList.indexOf(newExercise));
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        String idOfChanged = snapshot.getKey();
        int exIndex = -1;
        ExerciseInfo changedExercise = null;

        //Get index of exercise that was changed
        for(ActivityInfo exercise : this.exerciseList){
            if(idOfChanged.equals(exercise.getId())){
                exIndex = this.exerciseList.indexOf(exercise);
                changedExercise = (ExerciseInfo) exercise;
            }
        }

        //If exercise is not found in ArrayList
        if(changedExercise == null)
            return;
        //TODO print error occurred

        //If exercise name in ArrayList does not equal firebase entry, update name in ArrayList
        if(!changedExercise.getName().equals(snapshot.child(ExerciseInfo.exName).getValue(String.class)))
            changedExercise.setName(snapshot.child(ExerciseInfo.exName).getValue(String.class));

        //If exercise description in ArrayList does not equal firebase entry, update description in ArrayList
        if(!changedExercise.getDescription().equals(snapshot.child(ExerciseInfo.exDescription).getValue(String.class)))
            changedExercise.setDescription(snapshot.child(ExerciseInfo.exDescription).getValue(String.class));

        //If exercise max weight in ArrayList does not equal firebase entry, update max weight in ArrayList
        if(!changedExercise.getMaxWeight().equals(snapshot.child(ExerciseInfo.exMaxWeight).getValue(String.class)))
            changedExercise.setMaxWeight(snapshot.child(ExerciseInfo.exMaxWeight).getValue(String.class));

        //If exercise recommended reps in ArrayList does not equal firebase entry, update recommended reps in ArrayList
        if(!changedExercise.getRecommendedReps().equals(snapshot.child(ExerciseInfo.exRecReps).getValue(String.class)))
            changedExercise.setRecommendedReps(snapshot.child(ExerciseInfo.exRecReps).getValue(String.class));

        //If exercise recommended sets in ArrayList does not equal firebase entry, update recommended sets in ArrayList
        if(!changedExercise.getRecommendedSets().equals(snapshot.child(ExerciseInfo.exRecSets).getValue(String.class)))
            changedExercise.setRecommendedSets(snapshot.child(ExerciseInfo.exRecSets).getValue(String.class));

        //Notify Recycler adapter of changed item
        this.adapter.notifyItemChanged(exIndex);
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
        String idOfRemoved = snapshot.getKey();
        int exIndex = -1;

        //Get index of exercise that was removed
        for(ActivityInfo exercise : this.exerciseList)
            if(idOfRemoved.equals(exercise.getId()))
                exIndex = this.exerciseList.indexOf(exercise);


        //Remove ExerciseInfo from ArrayList
        exerciseList.remove(exIndex);

        //Notify recycler adapter of removed item
        this.adapter.notifyItemRemoved(exIndex);
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
