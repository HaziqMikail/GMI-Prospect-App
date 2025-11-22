package com.week4.gmiprospectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EnquiryActivity extends AppCompatActivity {

    CardView cardContactUs, cardAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);

        cardContactUs = findViewById(R.id.cardContactUs);
        cardAboutUs = findViewById(R.id.cardAboutUs);

        // Navigate to Contact Us
        cardContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnquiryActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to About Us
        cardAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnquiryActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }
}