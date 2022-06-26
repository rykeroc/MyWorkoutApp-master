package com.example.myworkoutapp.listviews;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.myworkoutapp.R;

//TODO Implement
public class CheckableViewHandler extends View {
    private final ViewGroup parent;
    private final View checkableView;

    private CardView cardView;
    private TextView itemName;
    private ImageButton checkImage;

    private boolean isChecked;

    /*
    TODO:
        Toggle on long click
        Show delete button when true, hide when false
     */
    private boolean longClickToggle;

    /*
    Types:
        1 for toggle on click
        2 for toggle on long click
     */
    private final int toggleType;


    //Color
    private final int backgroundColor = getResources().getColor(R.color.unchecked_color);
    private final int textColor = getResources().getColor(R.color.unchecked_text);
    //Text Size
    private final int buttonTextSize = (int) getResources().getDimension(R.dimen.button_text_size);

    //TODO Show exercise information on click

    //Background shape
    private GradientDrawable shape;


    //Constructor for Deletable button
    public CheckableViewHandler(Context context, ViewGroup parent, int toggleType){
        super(context);
        this.parent = parent;
        this.toggleType = toggleType;
        this.isChecked = false;

        //Inflate xml layout deletable_view
        LayoutInflater inflater = LayoutInflater.from(context);
        this.checkableView = inflater.inflate(R.layout.checkable_view, parent, false);
        this.itemName = checkableView.findViewById(R.id.checkableCardName);
        this.checkImage = checkableView.findViewById(R.id.checkStatus);
        this.checkImage.setVisibility(View.INVISIBLE);

        this.cardView = checkableView.findViewById(R.id.checkableCard);

        setOnClickListeners();
//        //TODO setup image view


    }

    public View getCheckableView() {
        return checkableView;
    }

    public void setText(String text) {
        this.itemName.setText(text);
    }

    //Set the layout's attributes
    private void setLayoutAttributes(){
        //Set toggle on click
        setOnClickListeners();
        //Set Layout Params
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,                                 //Width
            (int) getResources().getDimension(R.dimen.button_height)                    //Height
        );
        params.setMargins(10,10,10,10);   //Margins
        super.setLayoutParams(params);
        //Set button shape properties
        //Padding
        super.setPadding(20,2,20,2);
    }

    private void toggleOnLongClick(){
        //Toggle visibility
        if(this.longClickToggle){
            this.checkImage.setVisibility(View.VISIBLE);
            this.cardView.setCardBackgroundColor(getResources().getColor(R.color.checked_color));
            this.itemName.setTextColor(getResources().getColor(R.color.checked_text));
            this.checkImage.setColorFilter(getResources().getColor(R.color.checked_text));
        }
        else{
            this.checkImage.setVisibility(View.INVISIBLE);
            this.cardView.setCardBackgroundColor(getResources().getColor(R.color.unchecked_color));
            this.itemName.setTextColor(getResources().getColor(R.color.unchecked_text));
            this.checkImage.setColorFilter(getResources().getColor(R.color.unchecked_text));
        }
    }

    //Sets on click listeners
    private void setOnClickListeners() {
        if(this.toggleType == 1)
            //Toggle on long click
            this.cardView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckableViewHandler.this.longClickToggle = !CheckableViewHandler.this.longClickToggle;
                    toggleOnLongClick();
                }
            });
        else
            //Toggle on long click
            this.cardView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    CheckableViewHandler.this.longClickToggle = !CheckableViewHandler.this.longClickToggle;
                    toggleOnLongClick();

                    return CheckableViewHandler.this.longClickToggle;
                }
            });

        //TODO: set to view item on click

    }
}
