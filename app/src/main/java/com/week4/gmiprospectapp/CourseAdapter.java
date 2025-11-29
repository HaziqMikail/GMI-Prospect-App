package com.week4.gmiprospectapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> courseList;
    private ArrayList<String> descList;
    private OnItemClickListener listener;

    // Interface for click events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CourseAdapter(Context context, ArrayList<String> courseList, ArrayList<String> descList) {
        this.context = context;
        this.courseList = courseList;
        this.descList = descList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = courseList.get(position);
        String desc = descList.get(position);

        holder.tvTitle.setText(title);

        // SECTION HEADER LOGIC:
        // If the title contains "━━━", we make it look like a header, not a card.
        if (title.contains("━━━")) {
            holder.card.setCardElevation(0);
            holder.card.setCardBackgroundColor(Color.TRANSPARENT);
            holder.card.setStrokeWidth(0);
            holder.tvDesc.setVisibility(View.GONE); // Hide description for headers
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.gmi_blue_primary));
            holder.tvTitle.setTextSize(14);
            holder.card.setClickable(false); // Headers shouldn't be clickable
        } else {
            // Normal Card Style
            holder.card.setCardElevation(4);
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.surface_white));
            holder.tvDesc.setVisibility(View.VISIBLE);
            holder.tvDesc.setText(desc);
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.text_title));
            holder.tvTitle.setTextSize(16);
            holder.card.setClickable(true);
        }
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc;
        MaterialCardView card;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvDesc = itemView.findViewById(R.id.tvCourseDesc);
            card = itemView.findViewById(R.id.cardCourse);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}