package com.week4.gmiprospectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CoursesActivity extends AppCompatActivity {

    ListView courseList;
    HashMap<String, String> courseLinks; // Map each course to a URL
    HashMap<String, String> courseDescriptions; // Map each course to description
    Button btnCheckEligibility, btnApplyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        courseList = findViewById(R.id.courseListView);
        btnCheckEligibility = findViewById(R.id.btnCheckEligibility);
        btnApplyNow = findViewById(R.id.btnApplyNow);

        ArrayList<String> courses = new ArrayList<>();
        courseLinks = new HashMap<>();
        courseDescriptions = new HashMap<>();

        // ============= ELECTRICAL ENGINEERING =============
        courses.add("━━━ ELECTRICAL ENGINEERING ━━━");

        courses.add("Diploma in Mechatronics Engineering Technology");
        courseLinks.put("Diploma in Mechatronics Engineering Technology",
                "https://www.gmi.edu.my/diploma-in-mechatronics-engineering-technology/");
        courseDescriptions.put("Diploma in Mechatronics Engineering Technology",
                "Combination of mechanical, electrical and computer systems");

        courses.add("Diploma in Sustainable Energy & Power Distribution");
        courseLinks.put("Diploma in Sustainable Energy & Power Distribution",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Sustainable Energy & Power Distribution",
                "Focus on renewable energy and power systems");

        courses.add("Diploma in Electronics & Information Technology");
        courseLinks.put("Diploma in Electronics & Information Technology",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Electronics & Information Technology",
                "Computer hardware and electronics systems");

        courses.add("Diploma in Process Instrumentation & Control");
        courseLinks.put("Diploma in Process Instrumentation & Control",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Process Instrumentation & Control",
                "Industrial automation and control systems");

        courses.add("Diploma in Autotronics Engineering Technology");
        courseLinks.put("Diploma in Autotronics Engineering Technology",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Autotronics Engineering Technology",
                "Automotive electronics and systems");

        // ============= MECHANICAL ENGINEERING =============
        courses.add("━━━ MECHANICAL ENGINEERING ━━━");

        courses.add("Diploma in Product Design & Manufacturing");
        courseLinks.put("Diploma in Product Design & Manufacturing",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Product Design & Manufacturing",
                "Product development and manufacturing processes");

        courses.add("Diploma in Industrial Design");
        courseLinks.put("Diploma in Industrial Design",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Industrial Design",
                "Creative design for industrial products");

        courses.add("Diploma in Tool & Die Technology");
        courseLinks.put("Diploma in Tool & Die Technology",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Tool & Die Technology",
                "Precision tooling and die making");

        courses.add("Diploma in Mould Technology");
        courseLinks.put("Diploma in Mould Technology",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Mould Technology",
                "Plastic injection mould design and manufacturing");

        courses.add("Diploma in CNC Precision Technology");
        courseLinks.put("Diploma in CNC Precision Technology",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in CNC Precision Technology",
                "Computer numerical control machining");

        courses.add("Diploma in Industrial Quality Management");
        courseLinks.put("Diploma in Industrial Quality Management",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Industrial Quality Management",
                "Quality assurance and management systems");

        courses.add("Diploma in Manufacturing System");
        courseLinks.put("Diploma in Manufacturing System",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Manufacturing System",
                "Integrated manufacturing systems and processes");

        courses.add("Diploma in Sheet Metal Fabrication");
        courseLinks.put("Diploma in Sheet Metal Fabrication",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Sheet Metal Fabrication",
                "Sheet metal working and fabrication techniques");

        courses.add("Diploma in Machine Tools Maintenance");
        courseLinks.put("Diploma in Machine Tools Maintenance",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Machine Tools Maintenance",
                "Maintenance and repair of industrial machinery");

        // ============= COMPUTER & INFORMATION TECHNOLOGY =============
        courses.add("━━━ COMPUTER & IT ━━━");

        courses.add("Diploma in Software Engineering");
        courseLinks.put("Diploma in Software Engineering",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Software Engineering",
                "Software development and programming");

        courses.add("Diploma in Network Security (Cybersecurity)");
        courseLinks.put("Diploma in Network Security (Cybersecurity)",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Network Security (Cybersecurity)",
                "Cybersecurity and network protection");

        courses.add("Diploma in Creative Multimedia");
        courseLinks.put("Diploma in Creative Multimedia",
                "https://www.gmi.edu.my/diploma-programmes/");
        courseDescriptions.put("Diploma in Creative Multimedia",
                "Digital media, graphics and web design");

        // ============= FOUNDATION PROGRAMMES =============
        courses.add("━━━ FOUNDATION PROGRAMMES ━━━");

        courses.add("GUFP - GMI-UTP Foundation Programme");
        courseLinks.put("GUFP - GMI-UTP Foundation Programme",
                "https://www.gmi.edu.my/foundation-in-collaboration-with-universiti-teknologi-petronas/");
        courseDescriptions.put("GUFP - GMI-UTP Foundation Programme",
                "One year foundation for UTP undergraduate programs");

        courses.add("GAPP - German A-Level Preparatory Programme");
        courseLinks.put("GAPP - German A-Level Preparatory Programme",
                "https://www.gmi.edu.my/german-levels-preparatory-programme/");
        courseDescriptions.put("GAPP - German A-Level Preparatory Programme",
                "Pathway to German Universities of Applied Sciences");

        // Set up adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, courses);
        courseList.setAdapter(adapter);

        // Click listener
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = courses.get(position);

                // Ignore section headers
                if (selected.startsWith("━━━")) {
                    return;
                }

                // Get course information
                String url = courseLinks.get(selected);
                String description = courseDescriptions.get(selected);

                if (url != null) {
                    // Show toast with course description
                    Toast.makeText(CoursesActivity.this,
                            selected + "\n" + description,
                            Toast.LENGTH_LONG).show();

                    // Open course URL in browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    Toast.makeText(CoursesActivity.this,
                            "Course information not available",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}