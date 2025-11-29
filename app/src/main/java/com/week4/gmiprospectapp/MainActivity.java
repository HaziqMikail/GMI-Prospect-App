package com.week4.gmiprospectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View btnCourses, btnEligibility, btnEnquiry, btnAbout;
    private Animation clickAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Match IDs from activity_main.xml
        btnCourses = findViewById(R.id.btnCourses);
        btnEligibility = findViewById(R.id.btnEligibility);
        btnEnquiry = findViewById(R.id.btnEnquiry);
        btnAbout = findViewById(R.id.btnAbout);

        // Load click animation
        clickAnim = AnimationUtils.loadAnimation(this, R.anim.scale_click);

        // COURSES
        btnCourses.setOnClickListener(v -> {
            v.startAnimation(clickAnim);
            startActivity(new Intent(MainActivity.this, CoursesActivity.class));
        });

        // ELIGIBILITY
        btnEligibility.setOnClickListener(v -> {
            v.startAnimation(clickAnim);
            startActivity(new Intent(MainActivity.this, EligibilityActivity.class));
        });

        // ENQUIRY
        btnEnquiry.setOnClickListener(v -> {
            v.startAnimation(clickAnim);
            startActivity(new Intent(MainActivity.this, EnquiryActivity.class));
        });

        // ABOUT
        btnAbout.setOnClickListener(v -> {
            v.startAnimation(clickAnim);
            startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
        });
    }
}
