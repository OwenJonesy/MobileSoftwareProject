package com.example.drivingschoolapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Room database class for managing Booking entities
@Database(entities = {Booking.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static final String DATABASE_NAME = "DrivingSchoolBookings";

    // Singleton pattern to ensure a single instance of the database
    public synchronized static RoomDB getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries() // For simplicity, allows database operations on the main thread
                    .fallbackToDestructiveMigration() // Recreates the database if migrations fail
                    .build();
        }
        return database;
    }

    // Abstract method to access the DAO (Data Access Object) for Booking entities
    public abstract BookingDao bookingDao();
}
