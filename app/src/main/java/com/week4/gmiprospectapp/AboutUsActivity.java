package com.week4.gmiprospectapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    private TextView tvHistory, tvMission, tvVision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Connect views
        tvHistory = findViewById(R.id.tvHistory);
        tvMission = findViewById(R.id.tvMission);
        tvVision = findViewById(R.id.tvVision);

        // Set About Us info
        tvHistory.setText("GMI was established in 2005 with a vision to provide quality education and professional training for students worldwide.");
        tvMission.setText("Our mission is to empower learners with knowledge and skills to excel in their careers.");
        tvVision.setText("Our vision is to be a globally recognized institution for excellence in education and innovation.");
    }
}