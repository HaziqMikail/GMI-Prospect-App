package com.example.gmiprospectapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class EnquiryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);

        Button btnAboutUs = findViewById(R.id.btnAboutUs);
        Button btnContactUs = findViewById(R.id.btnContactUs);

        btnAboutUs.setOnClickListener(v -> startActivity(new Intent(this, AboutUsActivity.class)));
        btnContactUs.setOnClickListener(v -> startActivity(new Intent(this, ContactUsActivity.class)));
    }
}
