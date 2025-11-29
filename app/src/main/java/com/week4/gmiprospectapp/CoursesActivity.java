package com.week4.gmiprospectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class CoursesActivity extends AppCompatActivity {

    // 1. Declare variables at the TOP of the class
    RecyclerView recyclerView;
    CourseAdapter adapter;
    ArrayList<String> courseList;
    ArrayList<String> descList;
    HashMap<String, String> courseLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        // 2. Connect the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. Initialize Lists
        courseList = new ArrayList<>();
        descList = new ArrayList<>();
        courseLinks = new HashMap<>();

        // 4. Load Data
        loadCourseData();

        // 5. Setup Adapter
        adapter = new CourseAdapter(this, courseList, descList);
        recyclerView.setAdapter(adapter);

        // 6. Handle Clicks (Clean Implementation)
        adapter.setOnItemClickListener(position -> {
            // Get the name of the clicked course
            String selected = courseList.get(position);

            // If it's a section header (contains line), do nothing
            if (selected.contains("━━━")) return;

            // Get URL and open it
            String url = courseLinks.get(selected);
            if (url != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else {
                Toast.makeText(this, "Details coming soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Data Loading Helper
    private void loadCourseData() {
        // ============= ELECTRICAL =============
        addHeader("━━━ ELECTRICAL ENGINEERING ━━━");
        addCourse("Diploma in Mechatronics", "Mechanical, electrical & computer systems", "https://www.gmi.edu.my/diploma-in-mechatronics-engineering-technology/");
        addCourse("Diploma in Sustainable Energy", "Renewable energy & power distribution", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Electronics & IT", "Hardware & electronics systems", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Process Instrumentation", "Industrial automation & control", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Autotronics", "Automotive electronics technology", "https://www.gmi.edu.my/diploma-programmes/");

        // ============= MECHANICAL =============
        addHeader("━━━ MECHANICAL ENGINEERING ━━━");
        addCourse("Diploma in Product Design", "Manufacturing & product development", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Industrial Design", "Creative design for industry", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Tool & Die", "Precision tooling technology", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Mould Technology", "Injection mould design & making", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in CNC Precision", "Computer Numerical Control machining", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Manufacturing System", "Integrated manufacturing processes", "https://www.gmi.edu.my/diploma-programmes/");

        // ============= COMPUTER & IT =============
        addHeader("━━━ COMPUTER & IT ━━━");
        addCourse("Diploma in Software Engineering", "Coding, apps & system development", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Network Security", "Cybersecurity & network protection", "https://www.gmi.edu.my/diploma-programmes/");
        addCourse("Diploma in Creative Multimedia", "Graphics, web & digital media", "https://www.gmi.edu.my/diploma-programmes/");

        // ============= FOUNDATION =============
        addHeader("━━━ PRE-UNIVERSITY ━━━");
        addCourse("GUFP Foundation Programme", "Pathway to UTP (Universiti Teknologi Petronas)", "https://www.gmi.edu.my/foundation-in-collaboration-with-universiti-teknologi-petronas/");
        addCourse("GAPP German A-Level", "Pathway to German Universities", "https://www.gmi.edu.my/german-levels-preparatory-programme/");
    }

    private void addCourse(String title, String desc, String url) {
        courseList.add(title);
        descList.add(desc);
        courseLinks.put(title, url);
    }

    private void addHeader(String title) {
        courseList.add(title);
        descList.add("");
    }
}