package com.example.gmiprospectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CoursesActivity extends AppCompatActivity {

    ListView courseList;
    HashMap<String, String> courseLinks; // Map each course to a URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        courseList = findViewById(R.id.courseListView);

        ArrayList<String> courses = new ArrayList<>();
        courseLinks = new HashMap<>();

        // Electrical Engineering
        courses.add("=== Electrical Engineering ===");
        courses.add("Diploma of Mechatronics Engineering Technology");
        courseLinks.put("Diploma of Mechatronics Engineering Technology", "PLACEHOLDER_LINK_1");

        courses.add("Diploma in Engineering Technology (Sustainable Energy and Power Distribution)");
        courseLinks.put("Diploma in Engineering Technology (Sustainable Energy and Power Distribution)", "PLACEHOLDER_LINK_2");

        courses.add("Diploma of Electronics Engineering Technology (Computer)");
        courseLinks.put("Diploma of Electronics Engineering Technology (Computer)", "PLACEHOLDER_LINK_3");

        courses.add("Diploma of Engineering Technology (Instrumentation and Control)");
        courseLinks.put("Diploma of Engineering Technology (Instrumentation and Control)", "PLACEHOLDER_LINK_4");

        courses.add("Diploma in Autotronics Engineering Technology");
        courseLinks.put("Diploma in Autotronics Engineering Technology", "PLACEHOLDER_LINK_5");

        // Mechanical Engineering
        courses.add("=== Mechanical Engineering ===");
        courses.add("Diploma in Precision Tooling Engineering Technology");
        courseLinks.put("Diploma in Precision Tooling Engineering Technology", "PLACEHOLDER_LINK_6");

        courses.add("Diploma in Engineering Technology (Industrial Design)");
        courseLinks.put("Diploma in Engineering Technology (Industrial Design)", "PLACEHOLDER_LINK_7");

        courses.add("Diploma in Industrial Quality Engineering Technology");
        courseLinks.put("Diploma in Industrial Quality Engineering Technology", "PLACEHOLDER_LINK_8");

        courses.add("Diploma in Innovative Product Design Engineering Technology");
        courseLinks.put("Diploma in Innovative Product Design Engineering Technology", "PLACEHOLDER_LINK_9");

        courses.add("Diploma of Mechanical Engineering Technology (CNC Precision)");
        courseLinks.put("Diploma of Mechanical Engineering Technology (CNC Precision)", "PLACEHOLDER_LINK_10");

        courses.add("Diploma in Engineering Technology (Machine Tools Maintenance)");
        courseLinks.put("Diploma in Engineering Technology (Machine Tools Maintenance)", "PLACEHOLDER_LINK_11");

        courses.add("Diploma of Mechanical Engineering Technology (Manufacturing)");
        courseLinks.put("Diploma of Mechanical Engineering Technology (Manufacturing)", "PLACEHOLDER_LINK_12");

        // Computer and Information
        courses.add("=== Computer and Information ===");
        courses.add("Diploma in Software Engineering");
        courseLinks.put("Diploma in Software Engineering", "PLACEHOLDER_LINK_13");

        courses.add("Diploma in Cyber Security Technology");
        courseLinks.put("Diploma in Cyber Security Technology", "PLACEHOLDER_LINK_14");

        courses.add("Diploma in Creative Multimedia");
        courseLinks.put("Diploma in Creative Multimedia", "PLACEHOLDER_LINK_15");

        // Foundation Programmes
        courses.add("=== Foundation Programmes ===");
        courses.add("Foundation Programme in Collaboration with Universiti Teknologi PETRONAS (GUFP)");
        courseLinks.put("Foundation Programme in Collaboration with Universiti Teknologi PETRONAS (GUFP)", "PLACEHOLDER_LINK_16");

        courses.add("German A Levels Preparatory Programme (GAPP)");
        courseLinks.put("German A Levels Preparatory Programme (GAPP)", "PLACEHOLDER_LINK_17");

        // Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, courses);
        courseList.setAdapter(adapter);

        // Click listener
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = courses.get(position);

                // Ignore headers
                if (selected.startsWith("===")) return;

                // Placeholder action: open course URL
                String url = courseLinks.get(selected);
                if (url != null && !url.equals("PLACEHOLDER_LINK_" + (position + 1))) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else {
                    Toast.makeText(CoursesActivity.this,
                            "Click detected: " + selected + "\nURL not set yet.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
