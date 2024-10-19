package com.example.drivingschoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Main activity responsible for handling navigation and displaying content
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int currentImageIndex = 0;
    private int[] imageResources = {R.drawable.image1, R.drawable.image2, R.drawable.image3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        Button buttonInstructors = findViewById(R.id.buttonInstructors);
        Button buttonBooking = findViewById(R.id.buttonBooking);
        Button buttonMap = findViewById(R.id.buttonMap);
        Button buttonChangeImage = findViewById(R.id.buttonChangeImage);

        imageView = findViewById(R.id.imageView);

        // Set click listeners for each button
        buttonInstructors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch InstructorsActivity with instructor details
                launchInstructorsActivity();
            }
        });

        buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch BookingsActivity for managing bookings
                launchBookingsActivity();
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch MapsActivity for map navigation
                launchMapsActivity();
            }
        });

        buttonChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the displayed image
                changeImage();
            }
        });
    }

    // Display a short toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Change the displayed image when the button is clicked
    private void changeImage() {
        currentImageIndex = (currentImageIndex + 1) % imageResources.length;
        imageView.setImageResource(imageResources[currentImageIndex]);
        Log.d("ImageChange", "Image changed to: " + currentImageIndex);
    }

    // Launch InstructorsActivity with instructor details
    private void launchInstructorsActivity() {
        Intent intent = new Intent(MainActivity.this, InstructorsActivity.class);
        // Add instructor details as extras to the intent
        intent.putExtra("instructorName1", "Paul Johnson");
        intent.putExtra("instructorImageResId1", R.drawable.instructor1);
        intent.putExtra("instructorDetails1", "Introducing our dedicated instructor, Mr. Johnson! With years of expertise in driver education, Mr. Johnson is committed to helping you become a skilled and confident driver. His personalized approach to teaching ensures that each student receives the attention they need to excel. Mr. Johnson believes in creating a positive and supportive learning environment, making your journey to becoming a licensed driver both enjoyable and educational. Join Mr. Johnson's class today and embark on the road to driving success");

        intent.putExtra("instructorName2", "Tom Smith");
        intent.putExtra("instructorImageResId2", R.drawable.instructor2);
        intent.putExtra("instructorDetails2", "Meet our experienced instructor, Mr. Smith! With a passion for safe and enjoyable driving, Mr. Smith brings a wealth of knowledge to our driving school. He has successfully guided countless learners to become confident and skilled drivers. His friendly demeanor and effective teaching methods make every lesson an engaging experience. Whether you're navigating busy city streets or mastering the open road, Mr. Smith is dedicated to helping you become a skilled and responsible driver. Join his class and embark on your journey to driving success!");
        startActivity(intent);
    }

    // Launch BookingsActivity for managing bookings
    private void launchBookingsActivity() {
        Intent intent = new Intent(MainActivity.this, BookingsActivity.class);
        startActivity(intent);
    }

    // Launch MapsActivity for map navigation
    private void launchMapsActivity() {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }
}
