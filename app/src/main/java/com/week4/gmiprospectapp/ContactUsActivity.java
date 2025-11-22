package com.week4.gmiprospectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    private TextView tvEmail, tvPhone;
    private EditText etName, etMessage;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // Connect views
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        etName = findViewById(R.id.etName);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        // Set contact info
        tvEmail.setText("contact@gmi.com");
        tvPhone.setText("+1 234 567 890");

        // Button click
        btnSend.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String message = etMessage.getText().toString().trim();

            if(name.isEmpty() || message.isEmpty()){
                Toast.makeText(ContactUsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Open email app
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:contact@gmi.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GMI Enquiry from " + name);
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);

            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        });
    }
}
