package com.example.drivingschoolapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;

// Activity responsible for managing and displaying bookings
public class BookingsActivity extends AppCompatActivity {
    EditText editTextName, editTextDate, editTextInstructor;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<Booking> bookingList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    BookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Initialize UI elements
        editTextName = findViewById(R.id.edit_text_name);
        editTextDate = findViewById(R.id.edit_text_date);
        editTextInstructor = findViewById(R.id.edit_text_instructor);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        // Initialize Room database and retrieve existing bookings
        database = RoomDB.getInstance(this);
        bookingList = database.bookingDao().getAll();

        // Set up RecyclerView and its adapter
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new BookingAdapter(BookingsActivity.this, bookingList);
        recyclerView.setAdapter(adapter);

        // Add booking button click listener
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input
                String sName = editTextName.getText().toString().trim();
                String sDate = editTextDate.getText().toString().trim();
                String sInstructor = editTextInstructor.getText().toString().trim();

                // Check if input fields are not empty
                if (!sName.equals("") && !sDate.equals("") && !sInstructor.equals("")) {
                    // Create a new booking
                    Booking booking = new Booking();
                    booking.setName(sName);
                    booking.setDate(sDate);
                    booking.setInstructor(sInstructor);

                    // Insert the booking into the database
                    database.bookingDao().insert(booking);

                    // Clear input fields
                    editTextName.setText("");
                    editTextDate.setText("");
                    editTextInstructor.setText("");

                    // Refresh the booking list and notify the adapter
                    bookingList.clear();
                    bookingList.addAll(database.bookingDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // Reset button click listener
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset all bookings in the database
                database.bookingDao().reset(bookingList);

                // Refresh the booking list and notify the adapter
                bookingList.clear();
                bookingList.addAll(database.bookingDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
