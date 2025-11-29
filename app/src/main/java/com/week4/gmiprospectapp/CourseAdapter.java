package com.week4.gmiprospectapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> courseList;
    private ArrayList<String> descList;
    private HashMap<String, String> courseLinks;
    private int expandedPosition = -1; // Track expanded card

    public CourseAdapter(Context context, ArrayList<String> courseList,
                         ArrayList<String> descList, HashMap<String, String> courseLinks) {
        this.context = context;
        this.courseList = courseList;
        this.descList = descList;
        this.courseLinks = courseLinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = courseList.get(position);
        String desc = descList.get(position);

        holder.tvTitle.setText(title);
        holder.tvDesc.setText(desc);

        if (title.contains("━━━")) {
            // Header style
            holder.card.setCardElevation(0);
            holder.card.setCardBackgroundColor(Color.TRANSPARENT);
            holder.tvDesc.setVisibility(View.GONE);
            holder.buttonContainer.setVisibility(View.GONE);
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.gmi_blue_primary));
            holder.tvTitle.setTextSize(14);
            holder.card.setClickable(false);
        } else {
            // Normal course
            boolean isExpanded = position == expandedPosition;
            holder.tvDesc.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            holder.buttonContainer.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

            holder.card.setCardElevation(4);
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.surface_white));
            holder.tvTitle.setTextColor(context.getResources().getColor(R.color.text_title));
            holder.tvTitle.setTextSize(16);
            holder.card.setClickable(true);

            // Toggle expand
            holder.card.setOnClickListener(v -> {
                expandedPosition = isExpanded ? -1 : position; // Collapse if already expanded
                notifyDataSetChanged();
            });

            // Open website
            holder.btnWebsite.setOnClickListener(v -> {
                String url = courseLinks.get(title);
                if (url != null) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }
            });

            // Open eligibility checker
            holder.btnEligibility.setOnClickListener(v -> {
                context.startActivity(new Intent(context, EligibilityActivity.class));
            });
        }
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc;
        MaterialCardView card;
        LinearLayout buttonContainer;
        Button btnWebsite, btnEligibility;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvDesc = itemView.findViewById(R.id.tvCourseDesc);
            card = itemView.findViewById(R.id.cardCourse);
            btnWebsite = itemView.findViewById(R.id.btnWebsite);
            btnEligibility = itemView.findViewById(R.id.btnEligibility);
            buttonContainer = itemView.findViewById(R.id.buttonContainer);
        }
    }
}
