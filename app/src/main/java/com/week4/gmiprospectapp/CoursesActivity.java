package com.week4.gmiprospectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
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
    Button btnEligibility;

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

        // 5. Setup Adapter (pass courseLinks)
        adapter = new CourseAdapter(this, courseList, descList, courseLinks);
        recyclerView.setAdapter(adapter);

        // 7. Connect Eligibility Button
        btnEligibility = findViewById(R.id.btnEligibility);
        btnEligibility.setOnClickListener(v -> {
            Intent intent = new Intent(CoursesActivity.this, EligibilityActivity.class);
            startActivity(intent);
        });
    }

    // Data Loading Helper
    private void loadCourseData() {
        // ============= ELECTRICAL =============
        addHeader("━━━ ELECTRICAL ENGINEERING ━━━");
        addCourse("Diploma in Mechatronics Engineering Technology",
                "Mechatronics engineering technology integrates mechanical engineering, electronics, and intelligent computer control to design and deploy smart technologies.",
                "https://www.gmi.edu.my/diploma-in-mechatronics-engineering-technology/");
        addCourse("Diploma in Sustainable Energy and Power Distribution",
                "This programme provides knowledge and hands-on skills for designing, installing, operating and enhancing sustainable energy systems.",
                "https://www.gmi.edu.my/electrical-engineering/sustainable-energy-power-distribution/");
        addCourse("Diploma of Electronic Engineering Technology (Computer)",
                "Equips students with a profound knowledge and skills of electronic circuits and systems knowledge of telecommunications and computer controlled systems needed to meet the 21 st century industrial demands.",
                "https://www.gmi.edu.my/electrical-engineering/electric-information-technology/");
        addCourse("Diploma in Engineering Technology (Instrumentation & Control)",
                "Provides students with sound theoretical and practical training in the operation and maintenance of automated process control and measurement systems to maintain a consistent product output.",
                "https://www.gmi.edu.my/electrical-engineering/process-instrumentation-control/");
        addCourse("Diploma in Autotronics",
                "Autotronics, or modern automotive technology, combines automobile systems with electronics, while hybrid technology uses two or more power sources to propel a vehicle.",
                "https://www.gmi.edu.my/electrical-engineering/autotronics-engineering-technology/");

        // ============= MECHANICAL =============
        addHeader("━━━ MECHANICAL ENGINEERING ━━━");
        addCourse("Diploma in Precision Tooling",
                "Trains students in Tool and Die, Sheet Metal, and Plastic Engineering, equipping them with skills in 2D/3D design, analysis, fabrication, maintenance, and production.",
                "https://www.gmi.edu.my/mechanical-engineering/precision-tooling/");
        addCourse("Diploma in Industrial Design",
                "The industrial design programme prepares students to be creative, practical, aesthetics-focused, collaborative problem-solver, and to conduct the design process from project brief to design implementation.",
                "https://www.gmi.edu.my/mechanical-engineering/industrial-design-2/");
        addCourse("Diploma in Industrial Quality",
                "Focuses on the knowledge and skills aspects in the field of inspection and quality engineering required by the industry.",
                "https://www.gmi.edu.my/mechanical-engineering/industrial-quality-engineering/");
        addCourse("Diploma in Innovative Product Design",
                "Focuses on product design concepts, presentation of new product ideas and advanced manufacturing applications for production.",
                "https://www.gmi.edu.my/mechanical-engineering/innovative-product-design/");
        addCourse("Diploma of Mechanical Engineering (CNC Precision)",
                "Programme emphasizes fundamental knowledge and skills in Computer Numerical Control (CNC) precision machining technologies and processes.",
                "https://www.gmi.edu.my/mechanical-engineering/cnc-precision-technology/");
        addCourse("Diploma in Engineering Technology (Machine Tools Maintenance)",
                "The programme responds to the shift from manual to CNC machining, rising production demands, and stricter quality standards.",
                "https://www.gmi.edu.my/mechanical-engineering/machine-tools-maintenance/");
        addCourse("Diploma of Mechanical Engineering (Manufacturing)",
                "Program that emphasizes the interrelationships between manufacturing equipment, processes and controls, and their integration into production factories.",
                "https://www.gmi.edu.my/mechanical-engineering/manufacturing-system/");

        // ============= COMPUTER & IT =============
        addHeader("━━━ COMPUTER & IT ━━━");
        addCourse("Diploma in Software Engineering",
                "Designed to build competencies in the software development process, emphasizing coordinated defect prevention and detection strategies to reduce risks, time, and costs.",
                "https://www.gmi.edu.my/computer-and-information/software-engineering/");
        addCourse("Diploma in Cyber Security Technology",
                "To develop the competencies required in the security of overall network architecture which includes design and write computer programs, use and maintain databases, configure and integrate various network security tools and install and secure computer networks.",
                "https://www.gmi.edu.my/computer-and-information/cyber-security-technology/");
        addCourse("Diploma in Creative Multimedia",
                "Diploma programme that familiarizes students with overall process of producing digital work art, particularly in 2D and 3D.",
                "https://www.gmi.edu.my/computer-and-information/creative-multimedia/");

        // ============= FOUNDATION =============
        addHeader("━━━ PRE-UNIVERSITY ━━━");
        addCourse("GUFP Foundation Programme",
                "GMI-UTP Foundation Programme (GUFP) is strategic partnership between GMI (German Malaysian Institute) and UTP (Universiti Teknologi PETRONAS). This programme is a one year foundation programme based on UTP foundation syllabus. Upon completion, students will be qualified to start their Undergraduate Programme at UTP.)",
                "https://www.gmi.edu.my/foundation-in-collaboration-with-universiti-teknologi-petronas/");
        addCourse("GAPP German A-Level Preparatory Programme",
                "Students will undergo a 22-month preparatory programme at GMI, and also a 6-month intensive German Language training at various language centres in Germany before they are accepted for enrolment at the University of Applied Sciences (UAS).",
                "https://www.gmi.edu.my/pre-university-programme/german-a-level-preparatory-programme-gapp/");
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
