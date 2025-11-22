package com.week4.gmiprospectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EligibilityActivity extends AppCompatActivity {

    RadioGroup qualificationGroup;
    RadioButton rbSPM, rbOLevel, rbSTAM, rbMatriculation, rbAPEL;
    EditText etMathGrade, etScienceGrade, etCredits;
    Button btnCheckEligibility, btnReset;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        // Initialize views
        qualificationGroup = findViewById(R.id.qualificationGroup);
        rbSPM = findViewById(R.id.rbSPM);
        rbOLevel = findViewById(R.id.rbOLevel);
        rbSTAM = findViewById(R.id.rbSTAM);
        rbMatriculation = findViewById(R.id.rbMatriculation);
        rbAPEL = findViewById(R.id.rbAPEL);

        etMathGrade = findViewById(R.id.etMathGrade);
        etScienceGrade = findViewById(R.id.etScienceGrade);
        etCredits = findViewById(R.id.etCredits);

        btnCheckEligibility = findViewById(R.id.btnCheckEligibility);
        btnReset = findViewById(R.id.btnReset);
        tvResults = findViewById(R.id.tvResults);

        btnCheckEligibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEligibility();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });
    }

    private void checkEligibility() {
        // Get selected qualification
        int selectedId = qualificationGroup.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Please select your qualification", Toast.LENGTH_SHORT).show();
            return;
        }

        String mathGrade = etMathGrade.getText().toString().trim().toUpperCase();
        String scienceGrade = etScienceGrade.getText().toString().trim().toUpperCase();
        String creditsStr = etCredits.getText().toString().trim();

        if (mathGrade.isEmpty() || scienceGrade.isEmpty() || creditsStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isEligible = false;
        ArrayList<String> eligibleCourses = new ArrayList<>();
        String qualificationType = "";

        // Check eligibility based on qualification type
        if (selectedId == R.id.rbSPM) {
            qualificationType = "SPM";
            int credits = Integer.parseInt(creditsStr);

            // SPM Requirements: 3 credits including Math + 1 Science/Technical
            if (credits >= 3 && isPassingGrade(mathGrade, "SPM") &&
                    isPassingGrade(scienceGrade, "SPM")) {
                isEligible = true;
                eligibleCourses = getAllDiplomaCourses();
            }

        } else if (selectedId == R.id.rbOLevel) {
            qualificationType = "O-Level";
            int credits = Integer.parseInt(creditsStr);

            // O-Level Requirements: 3 Cs, C in Math + Pass in Science/Technical
            if (credits >= 3 && mathGrade.equals("C") &&
                    (scienceGrade.equals("C") || scienceGrade.equals("B") || scienceGrade.equals("A"))) {
                isEligible = true;
                eligibleCourses = getAllDiplomaCourses();
            }

        } else if (selectedId == R.id.rbSTAM) {
            qualificationType = "STAM/STPM";
            // STAM/STPM: Pass with CGPA 2.00
            try {
                double cgpa = Double.parseDouble(creditsStr);
                if (cgpa >= 2.0 && isPassingGrade(mathGrade, "STAM") &&
                        isPassingGrade(scienceGrade, "STAM")) {
                    isEligible = true;
                    eligibleCourses = getAllDiplomaCourses();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid CGPA (e.g., 2.5)", Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (selectedId == R.id.rbMatriculation) {
            qualificationType = "Matriculation/Foundation";
            // Matriculation: CGPA 2.00
            try {
                double cgpa = Double.parseDouble(creditsStr);
                if (cgpa >= 2.0) {
                    isEligible = true;
                    eligibleCourses = getAllDiplomaCourses();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid CGPA (e.g., 2.5)", Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (selectedId == R.id.rbAPEL) {
            qualificationType = "APEL";
            // APEL: 3 years working experience
            isEligible = true;
            eligibleCourses = getAllDiplomaCourses();
        }

        // Display results
        displayResults(isEligible, qualificationType, eligibleCourses);
    }

    private boolean isPassingGrade(String grade, String qualType) {
        if (qualType.equals("SPM")) {
            return grade.matches("[A-C]"); // A, B, C are passing grades
        } else if (qualType.equals("O-Level")) {
            return grade.matches("[A-C]");
        } else if (qualType.equals("STAM")) {
            return !grade.isEmpty(); // Any grade is acceptable for STAM with CGPA
        }
        return false;
    }

    private ArrayList<String> getAllDiplomaCourses() {
        ArrayList<String> courses = new ArrayList<>();

        // Electrical Engineering
        courses.add("• Diploma in Mechatronics Engineering Technology");
        courses.add("• Diploma in Sustainable Energy & Power Distribution");
        courses.add("• Diploma in Electronics & Information Technology");
        courses.add("• Diploma in Process Instrumentation & Control");
        courses.add("• Diploma in Autotronics Engineering Technology");

        // Mechanical Engineering
        courses.add("• Diploma in Product Design & Manufacturing");
        courses.add("• Diploma in Industrial Design");
        courses.add("• Diploma in Tool & Die Technology");
        courses.add("• Diploma in Mould Technology");
        courses.add("• Diploma in CNC Precision Technology");
        courses.add("• Diploma in Industrial Quality Management");
        courses.add("• Diploma in Manufacturing System");
        courses.add("• Diploma in Sheet Metal Fabrication");
        courses.add("• Diploma in Machine Tools Maintenance");

        // Computer & IT
        courses.add("• Diploma in Software Engineering");
        courses.add("• Diploma in Network Security (Cybersecurity)");
        courses.add("• Diploma in Creative Multimedia");

        return courses;
    }

    private void displayResults(boolean isEligible, String qualType, ArrayList<String> courses) {
        if (isEligible) {
            StringBuilder result = new StringBuilder();
            result.append("🎉 CONGRATULATIONS! 🎉\n\n");
            result.append("You are ELIGIBLE for GMI programmes!\n\n");
            result.append("Based on your ").append(qualType).append(" qualification, ");
            result.append("you can apply for the following courses:\n\n");

            for (String course : courses) {
                result.append(course).append("\n");
            }

            tvResults.setText(result.toString());
            tvResults.setTextColor(getResources().getColor(android.R.color.holo_green_dark));

            // Show dialog to proceed with application
            showApplicationDialog();

        } else {
            tvResults.setText("❌ NOT ELIGIBLE\n\n" +
                    "Unfortunately, you do not meet the minimum requirements.\n\n" +
                    "Requirements:\n" +
                    "• SPM: 3 credits including Mathematics + 1 Science/Technical\n" +
                    "• O-Level: 3 Cs, C in Math + Pass Science/Technical\n" +
                    "• STPM/STAM: Pass (CGPA 2.00)\n" +
                    "• Matriculation: CGPA 2.00\n\n" +
                    "Please contact GMI for more information.");
            tvResults.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void showApplicationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Apply Now?")
                .setMessage("You are eligible! Would you like to proceed to the GMI Online Application Portal?")
                .setPositiveButton("Yes, Apply Now", (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://gmi.vialing.com/oa/login"));
                    startActivity(intent);
                })
                .setNegativeButton("Not Yet", null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void resetForm() {
        qualificationGroup.clearCheck();
        etMathGrade.setText("");
        etScienceGrade.setText("");
        etCredits.setText("");
        tvResults.setText("");
        Toast.makeText(this, "Form reset", Toast.LENGTH_SHORT).show();
    }
}