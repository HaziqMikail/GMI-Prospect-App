package com.week4.gmiprospectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCourses, btnEligibility, btnEnquiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        btnCourses = findViewById(R.id.btnCourses);
        btnEligibility = findViewById(R.id.btnEligibility);
        btnEnquiry = findViewById(R.id.btnEnquiry);

        // Button: View Courses
        btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });

        // Button: Eligibility Checker
        btnEligibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EligibilityActivity.class);
                startActivity(intent);
            }
        });

        // Button: Enquiry (Contact & About)
        btnEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EnquiryActivity.class);
                startActivity(intent);
            }
        });
    }
}
