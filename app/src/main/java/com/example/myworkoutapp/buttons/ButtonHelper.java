package com.example.myworkoutapp.buttons;

import android.widget.Button;

import java.util.Comparator;

public class ButtonHelper {
    //Comparator for button text (Ascending)
    public static Comparator<Button> buttonTextComparatorAsc = new Comparator<Button>() {
        @Override
        public int compare(Button button0, Button button1) {
            return button0.getText().toString().compareTo(button1.getText().toString());
        }
    };

    //Comparator for button text (Descending)
    public static Comparator<Button> buttonTextComparatorDes = new Comparator<Button>() {
        @Override
        public int compare(Button button0, Button button1) {
            return button1.getText().toString().compareTo(button0.getText().toString());
        }
    };
}
