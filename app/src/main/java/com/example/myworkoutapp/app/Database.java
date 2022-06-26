package com.example.myworkoutapp.app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public interface Database {
    FirebaseDatabase ROOT = FirebaseDatabase.getInstance();
    DatabaseReference EXERCISES = ROOT.getReference("exercises");
    DatabaseReference WORKOUTS = ROOT.getReference("workouts");
    DatabaseReference LOGS = ROOT.getReference("logs");
    DatabaseReference ACCOUNTS = ROOT.getReference("accounts");
}
