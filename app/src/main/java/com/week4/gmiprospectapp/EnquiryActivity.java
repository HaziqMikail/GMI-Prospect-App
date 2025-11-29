package com.week4.gmiprospectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class EnquiryActivity extends AppCompatActivity {

    private CardView cardContactUs, cardAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry); // uses your enquiry layout

        // Match IDs from activity_enquiry.xml
        cardContactUs = findViewById(R.id.cardContactUs);
        cardAboutUs = findViewById(R.id.cardAboutUs);

        // Go to Contact Us screen
        cardContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnquiryActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });

        // Go to About Us screen
        cardAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnquiryActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }
}
