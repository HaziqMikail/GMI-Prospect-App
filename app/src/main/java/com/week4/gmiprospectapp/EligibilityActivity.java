package com.week4.gmiprospectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EligibilityActivity extends AppCompatActivity {

    Spinner spinnerCourse;
    RadioGroup qualificationGroup;
    LinearLayout qualificationContainer;

    // Input fields that will be shown/hidden based on qualification
    LinearLayout layoutSPMFields, layoutCGPAFields, layoutGAPPFields;

    // SPM specific fields with spinners
    Spinner spMathGrade, spScienceGrade, spBM, spEnglish, spSejarah, spOtherSubjects;

    // CGPA fields (for STPM, Matriculation, SVM)
    EditText etCGPA;

    // GAPP specific fields with spinners
    Spinner spEngGrade, spModernMathGrade, spAddMathGrade, spPhysicsGrade, spChemistryGrade, spOtherGrade;

    Button btnCheckEligibility, btnReset;
    TextView tvResults, tvQualificationInfo;

    String selectedCourse = "";
    ArrayList<RadioButton> dynamicRadioButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);

        // Initialize views
        spinnerCourse = findViewById(R.id.spinnerCourse);
        qualificationGroup = findViewById(R.id.qualificationGroup);
        qualificationContainer = findViewById(R.id.qualificationContainer);
        tvQualificationInfo = findViewById(R.id.tvQualificationInfo);

        // Initialize layout containers
        layoutSPMFields = findViewById(R.id.layoutSPMFields);
        layoutCGPAFields = findViewById(R.id.layoutCGPAFields);
        layoutGAPPFields = findViewById(R.id.layoutGAPPFields);

        // SPM field spinners
        spMathGrade = findViewById(R.id.spMathGrade);
        spScienceGrade = findViewById(R.id.spScienceGrade);
        spBM = findViewById(R.id.spBM);
        spEnglish = findViewById(R.id.spEnglish);
        spSejarah = findViewById(R.id.spSejarah);
        spOtherSubjects = findViewById(R.id.spOtherSubjects);

        // CGPA field
        etCGPA = findViewById(R.id.etCGPA);

        // GAPP field spinners
        spEngGrade = findViewById(R.id.spEngGrade);
        spModernMathGrade = findViewById(R.id.spModernMathGrade);
        spAddMathGrade = findViewById(R.id.spAddMathGrade);
        spPhysicsGrade = findViewById(R.id.spPhysicsGrade);
        spChemistryGrade = findViewById(R.id.spChemistryGrade);
        spOtherGrade = findViewById(R.id.spOtherGrade);

        btnCheckEligibility = findViewById(R.id.btnCheckEligibility);
        btnReset = findViewById(R.id.btnReset);
        tvResults = findViewById(R.id.tvResults);

        // Setup grade spinners
        setupGradeSpinners();

        // Setup course spinner
        setupCourseSpinner();

        // Setup qualification radio group listener
        qualificationGroup.setOnCheckedChangeListener((group, checkedId) -> {
            updateFieldsVisibility(checkedId);
        });

        btnCheckEligibility.setOnClickListener(v -> checkEligibility());
        btnReset.setOnClickListener(v -> resetForm());

        // Hide all input fields initially
        layoutSPMFields.setVisibility(View.GONE);
        layoutCGPAFields.setVisibility(View.GONE);
        layoutGAPPFields.setVisibility(View.GONE);
        qualificationContainer.setVisibility(View.GONE);
    }

    private void setupGradeSpinners() {
        // Standard grades A-E
        String[] grades = {"Select Grade", "A", "B", "C", "D", "E"};
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, grades);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply to all grade spinners
        spMathGrade.setAdapter(gradeAdapter);
        spScienceGrade.setAdapter(gradeAdapter);
        spBM.setAdapter(gradeAdapter);
        spEnglish.setAdapter(gradeAdapter);
        spOtherSubjects.setAdapter(gradeAdapter);
        spEngGrade.setAdapter(gradeAdapter);
        spModernMathGrade.setAdapter(gradeAdapter);
        spAddMathGrade.setAdapter(gradeAdapter);
        spPhysicsGrade.setAdapter(gradeAdapter);
        spChemistryGrade.setAdapter(gradeAdapter);
        spOtherGrade.setAdapter(gradeAdapter);

        // Pass/Fail for Sejarah
        String[] passFail = {"Select", "Pass", "Fail"};
        ArrayAdapter<String> passFailAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, passFail);
        passFailAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSejarah.setAdapter(passFailAdapter);
    }

    private void setupCourseSpinner() {
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Select a Course");

        // Diploma courses
        courses.add("Diploma in Mechatronics Engineering Technology");
        courses.add("Diploma in Sustainable Energy & Power Distribution");
        courses.add("Diploma in Electronics & Information Technology");
        courses.add("Diploma in Process Instrumentation & Control");
        courses.add("Diploma in Autotronics Engineering Technology");
        courses.add("Diploma in Product Design & Manufacturing");
        courses.add("Diploma in Industrial Design");
        courses.add("Diploma in Precision Tooling");
        courses.add("Diploma in Industrial Quality Management");
        courses.add("Diploma in CNC Precision Technology");
        courses.add("Diploma in Machine Tools Maintenance");
        courses.add("Diploma in Manufacturing System");
        courses.add("Diploma in Software Engineering");
        courses.add("Diploma in Network Security (Cybersecurity)");
        courses.add("Diploma in Creative Multimedia");

        // Foundation courses
        courses.add("GMI-UTP Foundation Programme (GUFP)");
        courses.add("German A-Level Preparatory Programme (GAPP)");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(adapter);

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = courses.get(position);
                updateQualificationOptions();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCourse = "";
            }
        });
    }

    private void updateQualificationOptions() {
        // Clear previous radio buttons
        qualificationGroup.removeAllViews();
        dynamicRadioButtons.clear();

        // Hide all input layouts
        layoutSPMFields.setVisibility(View.GONE);
        layoutCGPAFields.setVisibility(View.GONE);
        layoutGAPPFields.setVisibility(View.GONE);
        tvResults.setText("");

        if (selectedCourse.isEmpty() || selectedCourse.equals("Select a Course")) {
            qualificationContainer.setVisibility(View.GONE);
            tvQualificationInfo.setText("");
            return;
        }

        qualificationContainer.setVisibility(View.VISIBLE);

        // Add qualification options based on course
        if (selectedCourse.equals("German A-Level Preparatory Programme (GAPP)")) {
            tvQualificationInfo.setText("Available qualifications for GAPP:");
            addRadioButton("SPM - Sponsored Candidate (All A's)", "GAPP_SPONSORED");
            addRadioButton("SPM - Private Candidate (Minimum C)", "GAPP_PRIVATE");

        } else if (selectedCourse.equals("GMI-UTP Foundation Programme (GUFP)")) {
            tvQualificationInfo.setText("Available qualifications for GUFP:");
            addRadioButton("SPM (Minimum C in core subjects)", "GUFP_SPM");
            addRadioButton("O-Level / IGCSE (Minimum C in 5 subjects)", "GUFP_OLEVEL");

        } else {
            // General Diploma courses
            tvQualificationInfo.setText("Available qualifications for Diploma programmes:");
            addRadioButton("SPM (3 Credits including Math + Science)", "DIPLOMA_SPM");
            addRadioButton("O-Level (3 Cs including Math + Science)", "DIPLOMA_OLEVEL");
            addRadioButton("STPM / STAM (CGPA 2.00)", "DIPLOMA_STPM");
            addRadioButton("Matriculation / Foundation (CGPA 2.00)", "DIPLOMA_MATRIC");
            addRadioButton("SVM - Sijil Vokasional Malaysia (CGPA 2.00)", "DIPLOMA_SVM");
        }
    }

    private void addRadioButton(String text, String tag) {
        RadioButton rb = new RadioButton(this);
        rb.setText(text);
        rb.setTag(tag);
        rb.setPadding(16, 16, 16, 16);
        rb.setTextSize(14);
        qualificationGroup.addView(rb);
        dynamicRadioButtons.add(rb);
    }

    private void updateFieldsVisibility(int checkedId) {
        // Hide all layouts first
        layoutSPMFields.setVisibility(View.GONE);
        layoutCGPAFields.setVisibility(View.GONE);
        layoutGAPPFields.setVisibility(View.GONE);

        // Find selected radio button
        RadioButton selectedRB = findViewById(checkedId);
        if (selectedRB == null) return;

        String tag = selectedRB.getTag().toString();

        // Show appropriate layout based on tag
        if (tag.equals("GAPP_SPONSORED") || tag.equals("GAPP_PRIVATE")) {
            layoutGAPPFields.setVisibility(View.VISIBLE);
        } else if (tag.startsWith("GUFP_") || tag.equals("DIPLOMA_SPM") || tag.equals("DIPLOMA_OLEVEL")) {
            layoutSPMFields.setVisibility(View.VISIBLE);
        } else if (tag.equals("DIPLOMA_STPM") || tag.equals("DIPLOMA_MATRIC") || tag.equals("DIPLOMA_SVM")) {
            layoutCGPAFields.setVisibility(View.VISIBLE);
        }
    }

    private void checkEligibility() {
        // Validate course selection
        if (selectedCourse.isEmpty() || selectedCourse.equals("Select a Course")) {
            Toast.makeText(this, "Please select a course", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected qualification
        int selectedId = qualificationGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select your qualification", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRB = findViewById(selectedId);
        String tag = selectedRB.getTag().toString();

        boolean isEligible = false;
        String message = "";

        // Check eligibility based on tag
        switch (tag) {
            case "GAPP_SPONSORED":
                isEligible = checkGAPPSponsored();
                message = getGAPPSponsoredMessage(isEligible);
                break;

            case "GAPP_PRIVATE":
                isEligible = checkGAPPPrivate();
                message = getGAPPPrivateMessage(isEligible);
                break;

            case "GUFP_SPM":
                isEligible = checkGUFPSPM();
                message = getGUFPMessage(isEligible);
                break;

            case "GUFP_OLEVEL":
                isEligible = checkGUFPOLevel();
                message = getGUFPMessage(isEligible);
                break;

            case "DIPLOMA_SPM":
                isEligible = checkDiplomaSPM();
                message = getDiplomaMessage(isEligible, "SPM");
                break;

            case "DIPLOMA_OLEVEL":
                isEligible = checkDiplomaOLevel();
                message = getDiplomaMessage(isEligible, "O-Level");
                break;

            case "DIPLOMA_STPM":
                isEligible = checkDiplomaCGPA();
                message = getDiplomaMessage(isEligible, "STPM/STAM");
                break;

            case "DIPLOMA_MATRIC":
                isEligible = checkDiplomaCGPA();
                message = getDiplomaMessage(isEligible, "Matriculation");
                break;

            case "DIPLOMA_SVM":
                isEligible = checkDiplomaCGPA();
                message = getDiplomaMessage(isEligible, "SVM");
                break;
        }

        displayResults(isEligible, message);
    }

    // GAPP Sponsored - All A's required
    private boolean checkGAPPSponsored() {
        String eng = getSpinnerValue(spEngGrade);
        String modernMath = getSpinnerValue(spModernMathGrade);
        String addMath = getSpinnerValue(spAddMathGrade);
        String physics = getSpinnerValue(spPhysicsGrade);
        String chemistry = getSpinnerValue(spChemistryGrade);
        String other = getSpinnerValue(spOtherGrade);

        if (eng.isEmpty() || modernMath.isEmpty() || addMath.isEmpty() ||
                physics.isEmpty() || chemistry.isEmpty() || other.isEmpty()) {
            Toast.makeText(this, "Please select all grades", Toast.LENGTH_SHORT).show();
            return false;
        }

        return eng.equals("A") && modernMath.equals("A") && addMath.equals("A") &&
                physics.equals("A") && chemistry.equals("A") && other.equals("A");
    }

    // GAPP Private - Minimum C
    private boolean checkGAPPPrivate() {
        String eng = getSpinnerValue(spEngGrade);
        String modernMath = getSpinnerValue(spModernMathGrade);
        String addMath = getSpinnerValue(spAddMathGrade);
        String physics = getSpinnerValue(spPhysicsGrade);
        String chemistry = getSpinnerValue(spChemistryGrade);
        String other = getSpinnerValue(spOtherGrade);

        if (eng.isEmpty() || modernMath.isEmpty() || addMath.isEmpty() ||
                physics.isEmpty() || chemistry.isEmpty() || other.isEmpty()) {
            Toast.makeText(this, "Please select all grades", Toast.LENGTH_SHORT).show();
            return false;
        }

        return isGradeAtLeast(eng, "C") && isGradeAtLeast(modernMath, "C") &&
                isGradeAtLeast(addMath, "C") && isGradeAtLeast(physics, "C") &&
                isGradeAtLeast(chemistry, "C") && isGradeAtLeast(other, "C");
    }

    // GUFP SPM
    private boolean checkGUFPSPM() {
        String bm = getSpinnerValue(spBM);
        String math = getSpinnerValue(spMathGrade);
        String english = getSpinnerValue(spEnglish);
        String science = getSpinnerValue(spScienceGrade);

        if (bm.isEmpty() || math.isEmpty() || english.isEmpty() || science.isEmpty()) {
            Toast.makeText(this, "Please select all grades", Toast.LENGTH_SHORT).show();
            return false;
        }

        return isGradeAtLeast(bm, "C") && isGradeAtLeast(math, "C") &&
                isGradeAtLeast(english, "C") && isGradeAtLeast(science, "C");
    }

    // GUFP O-Level
    private boolean checkGUFPOLevel() {
        String math = getSpinnerValue(spMathGrade);
        String science = getSpinnerValue(spScienceGrade);
        String other = getSpinnerValue(spOtherSubjects);

        if (math.isEmpty() || science.isEmpty() || other.isEmpty()) {
            Toast.makeText(this, "Please select all grades", Toast.LENGTH_SHORT).show();
            return false;
        }

        return isGradeAtLeast(math, "C") && isGradeAtLeast(science, "C") &&
                isGradeAtLeast(other, "C");
    }

    // Diploma SPM
    private boolean checkDiplomaSPM() {
        String math = getSpinnerValue(spMathGrade);
        String science = getSpinnerValue(spScienceGrade);
        String other = getSpinnerValue(spOtherSubjects);
        String sejarah = spSejarah.getSelectedItem().toString();

        if (math.isEmpty() || science.isEmpty() || other.isEmpty() || sejarah.equals("Select")) {
            Toast.makeText(this, "Please select all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Need 3 credits (A-C) and Pass Sejarah
        return isPassingGrade(math) && isPassingGrade(science) &&
                isPassingGrade(other) && sejarah.equals("Pass");
    }

    // Diploma O-Level
    private boolean checkDiplomaOLevel() {
        String math = getSpinnerValue(spMathGrade);
        String science = getSpinnerValue(spScienceGrade);
        String other = getSpinnerValue(spOtherSubjects);

        if (math.isEmpty() || science.isEmpty() || other.isEmpty()) {
            Toast.makeText(this, "Please select all grades", Toast.LENGTH_SHORT).show();
            return false;
        }

        return isGradeAtLeast(math, "C") && isGradeAtLeast(science, "C") &&
                isGradeAtLeast(other, "C");
    }

    // Diploma CGPA (STPM, Matriculation, SVM)
    private boolean checkDiplomaCGPA() {
        String cgpaStr = etCGPA.getText().toString().trim();

        if (cgpaStr.isEmpty()) {
            Toast.makeText(this, "Please enter CGPA", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            double cgpa = Double.parseDouble(cgpaStr);
            return cgpa >= 2.0;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid CGPA", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private String getSpinnerValue(Spinner spinner) {
        String value = spinner.getSelectedItem().toString();
        if (value.equals("Select Grade") || value.equals("Select")) {
            return "";
        }
        return value;
    }

    private boolean isPassingGrade(String grade) {
        return grade.matches("[A-C]");
    }

    private boolean isGradeAtLeast(String grade, String minimum) {
        if (grade.isEmpty()) return false;
        int gradeValue = getGradeValue(grade);
        int minValue = getGradeValue(minimum);
        return gradeValue <= minValue;
    }

    private int getGradeValue(String grade) {
        switch (grade) {
            case "A": return 1;
            case "B": return 2;
            case "C": return 3;
            case "D": return 4;
            case "E": return 5;
            default: return 10;
        }
    }

    // Message methods
    private String getGAPPSponsoredMessage(boolean isEligible) {
        if (isEligible) {
            return "🎉 CONGRATULATIONS! 🎉\n\n" +
                    "You are ELIGIBLE for GAPP (Sponsored)!\n\n" +
                    "You meet the requirements:\n" +
                    "✓ All subjects with Grade A\n" +
                    "✓ Must Pass Bahasa Melayu & Sejarah\n\n" +
                    "22-month prep at GMI + 6-month German training in Germany!";
        } else {
            return "❌ NOT ELIGIBLE\n\n" +
                    "GAPP Sponsored Requirements:\n" +
                    "• English: A\n" +
                    "• Modern Mathematics: A\n" +
                    "• Additional Mathematics: A\n" +
                    "• Physics: A\n" +
                    "• Chemistry: A\n" +
                    "• 2 Other Subjects: A\n" +
                    "• Bahasa Melayu: Pass\n" +
                    "• Sejarah: Pass";
        }
    }

    private String getGAPPPrivateMessage(boolean isEligible) {
        if (isEligible) {
            return "🎉 CONGRATULATIONS! 🎉\n\n" +
                    "You are ELIGIBLE for GAPP (Private)!\n\n" +
                    "You meet the requirements:\n" +
                    "✓ All subjects with minimum Grade C\n\n" +
                    "22-month prep at GMI + 6-month German training in Germany!";
        } else {
            return "❌ NOT ELIGIBLE\n\n" +
                    "GAPP Private Requirements:\n" +
                    "• English: C\n" +
                    "• Modern Mathematics: C\n" +
                    "• Additional Mathematics: C\n" +
                    "• Physics: C\n" +
                    "• Chemistry: C\n" +
                    "• 2 Other Subjects: C\n" +
                    "• Bahasa Melayu: Pass\n" +
                    "• Sejarah: Pass";
        }
    }

    private String getGUFPMessage(boolean isEligible) {
        if (isEligible) {
            return "🎉 CONGRATULATIONS! 🎉\n\n" +
                    "You are ELIGIBLE for GMI-UTP Foundation Programme!\n\n" +
                    "One-year foundation in collaboration with UTP.\n" +
                    "Upon completion, qualify for UTP Undergraduate Programme!";
        } else {
            return "❌ NOT ELIGIBLE\n\n" +
                    "GUFP Requirements:\n" +
                    "SPM: Minimum C in BM, Modern Math, English, Add Math, Physics, Chemistry\n" +
                    "O-Level: Minimum C in 5 subjects including Math, Physics, Chemistry";
        }
    }

    private String getDiplomaMessage(boolean isEligible, String qualType) {
        if (isEligible) {
            return "🎉 CONGRATULATIONS! 🎉\n\n" +
                    "You are ELIGIBLE for " + selectedCourse + "!\n\n" +
                    "Based on your " + qualType + " qualification.\n\n" +
                    "GMI Diploma programmes offer:\n" +
                    "• Technical excellence\n" +
                    "• German training & culture\n" +
                    "• Industry partnerships\n" +
                    "• Career opportunities";
        } else {
            return "❌ NOT ELIGIBLE\n\n" +
                    "Diploma Requirements for " + qualType + ":\n" +
                    getDiplomaRequirements(qualType);
        }
    }

    private String getDiplomaRequirements(String qualType) {
        switch (qualType) {
            case "SPM":
                return "• 3 Credits (A-C) including Math, Science/Technical, 1 other\n" +
                        "• Pass in BM, English, Sejarah";
            case "O-Level":
                return "• 3 Cs including Math, Science/Technical";
            case "STPM/STAM":
                return "• Pass with minimum CGPA 2.00\n" +
                        "• Pass Math, English, Science at SPM level";
            case "Matriculation":
                return "• Minimum CGPA 2.00";
            case "SVM":
                return "• CGPA 2.00\n" +
                        "• Pass Sejarah\n" +
                        "• 3 SPM Credits including BM\n" +
                        "• Competent in all technical modules";
            default:
                return "Contact GMI for requirements";
        }
    }

    private void displayResults(boolean isEligible, String message) {
        tvResults.setText(message);

        if (isEligible) {
            tvResults.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            showApplicationDialog();
        } else {
            tvResults.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void showApplicationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Apply Now?")
                .setMessage("You are eligible for " + selectedCourse +
                        "!\n\nWould you like to proceed to the GMI Online Application Portal?")
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
        spinnerCourse.setSelection(0);
        qualificationGroup.clearCheck();

        // Reset all spinners
        spMathGrade.setSelection(0);
        spScienceGrade.setSelection(0);
        spBM.setSelection(0);
        spEnglish.setSelection(0);
        spSejarah.setSelection(0);
        spOtherSubjects.setSelection(0);
        spEngGrade.setSelection(0);
        spModernMathGrade.setSelection(0);
        spAddMathGrade.setSelection(0);
        spPhysicsGrade.setSelection(0);
        spChemistryGrade.setSelection(0);
        spOtherGrade.setSelection(0);

        etCGPA.setText("");
        tvResults.setText("");

        // Hide all layouts
        layoutSPMFields.setVisibility(View.GONE);
        layoutCGPAFields.setVisibility(View.GONE);
        layoutGAPPFields.setVisibility(View.GONE);
        qualificationContainer.setVisibility(View.GONE);

        Toast.makeText(this, "Form reset", Toast.LENGTH_SHORT).show();
    }
}