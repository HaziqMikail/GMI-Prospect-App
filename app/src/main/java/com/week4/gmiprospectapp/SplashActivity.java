package com.week4.gmiprospectapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 1. Setup Animation (Fade In)
        ImageView logo = findViewById(R.id.imgLogo);
        TextView title = findViewById(R.id.tvAppName);
        TextView tagline = findViewById(R.id.tvTagline);

        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1200); // 1.2 seconds fade in

        // 2. Start Animation
        logo.startAnimation(fadeIn);
        title.startAnimation(fadeIn);
        tagline.startAnimation(fadeIn);

        // 3. Move to MainActivity after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            // Smooth transition between activities
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish(); // Prevent user from going back to splash
        }, SPLASH_DURATION);
    }
}