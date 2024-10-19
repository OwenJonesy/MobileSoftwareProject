package com.example.drivingschoolapp;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;
import java.util.List;

// Data Access Object (DAO) for interacting with the Booking entity in the Room database
@Dao
public interface BookingDao {
    // Inserts a new booking or replaces an existing one in case of a conflict
    @Insert(onConflict = REPLACE)
    void insert(Booking booking);

    // Deletes a specific booking from the database
    @Delete
    void delete(Booking booking);

    // Deletes a list of bookings from the database
    @Delete
    void reset(List<Booking> bookings);

    // Updates the details of a booking based on its unique ID
    @Query("UPDATE booking_table SET name = :name, date = :date, instructor = :instructor WHERE id = :id")
    void update(int id, String name, String date, String instructor);

    // Retrieves all bookings from the database
    @Query("SELECT * FROM booking_table")
    List<Booking> getAll();

    // Adds a new method for updating the date of a specific booking
    @Query("UPDATE booking_table SET date = :newDate WHERE id = :bookingId")
    void updateDate(int bookingId, String newDate);
}
