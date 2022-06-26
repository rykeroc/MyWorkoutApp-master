package com.example.myworkoutapp.listviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myworkoutapp.R;
import com.example.myworkoutapp.app.info.ActivityInfo;

import java.util.ArrayList;

public class CheckableViewAdapter extends RecyclerView.Adapter<CheckableViewAdapter.CheckableViewHolder> {
    private final Context context;
    private final ArrayList<ActivityInfo> listOfItems;
    /*
    Types:
        1 for Exercise
        2 for Logs
        3 for Workouts
     */
    private final int toggleType;

    public CheckableViewAdapter(Context context, ArrayList<ActivityInfo> listOfItems, int toggleType) {
        this.context = context;
        this.listOfItems = listOfItems;
        this.toggleType = toggleType;
    }

    @NonNull
    @Override
    public CheckableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CheckableViewHandler checkableViewHandler = new CheckableViewHandler(context, parent, toggleType);
        View view = checkableViewHandler.getCheckableView();
        return new CheckableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckableViewHolder holder, int position) {
        holder.itemName.setText(this.listOfItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.listOfItems.size();
    }

    public class CheckableViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        ImageButton checkImage;

        public CheckableViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemName = itemView.findViewById(R.id.checkableCardName);
            this.checkImage = itemView.findViewById(R.id.checkStatus);
        }
    }
}
