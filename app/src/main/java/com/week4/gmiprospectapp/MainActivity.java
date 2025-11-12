package com.example.gmiprospectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCourses = findViewById(R.id.btnCourses);
        Button btnEligibility = findViewById(R.id.btnEligibility);
        Button btnEnquiry = findViewById(R.id.btnEnquiry);

        btnCourses.setOnClickListener(v -> startActivity(new Intent(this, CoursesActivity.class)));
        btnEligibility.setOnClickListener(v -> startActivity(new Intent(this, EligibilityActivity.class)));
        btnEnquiry.setOnClickListener(v -> startActivity(new Intent(this, EnquiryActivity.class)));
    }
}
