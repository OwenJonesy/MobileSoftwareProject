package com.example.drivingschoolapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// Activity for displaying details of driving instructors
public class InstructorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors);

        // Retrieve data passed from MainActivity for the first instructor
        String instructorName1 = getIntent().getStringExtra("instructorName1");
        int instructorImageResId1 = getIntent().getIntExtra("instructorImageResId1", R.drawable.instructor1);
        String instructorDetails1 = getIntent().getStringExtra("instructorDetails1");

        // Retrieve data passed from MainActivity for the second instructor
        String instructorName2 = getIntent().getStringExtra("instructorName2");
        int instructorImageResId2 = getIntent().getIntExtra("instructorImageResId2", R.drawable.instructor2);
        String instructorDetails2 = getIntent().getStringExtra("instructorDetails2");

        // Set up views for the first instructor
        ImageView instructorImageView1 = findViewById(R.id.instructorImageView1);
        TextView instructorNameTextView1 = findViewById(R.id.instructorNameTextView1);
        TextView instructorDetailsTextView1 = findViewById(R.id.instructorDetailsTextView1);

        // Set data to views for the first instructor
        instructorImageView1.setImageResource(instructorImageResId1);
        instructorNameTextView1.setText(instructorName1);
        instructorDetailsTextView1.setText(instructorDetails1);

        // Set up views for the second instructor
        ImageView instructorImageView2 = findViewById(R.id.instructorImageView2);
        TextView instructorNameTextView2 = findViewById(R.id.instructorNameTextView2);
        TextView instructorDetailsTextView2 = findViewById(R.id.instructorDetailsTextView2);

        // Set data to views for the second instructor
        instructorImageView2.setImageResource(instructorImageResId2);
        instructorNameTextView2.setText(instructorName2);
        instructorDetailsTextView2.setText(instructorDetails2);
    }
}
