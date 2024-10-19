package com.example.drivingschoolapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Context;
import android.widget.ImageView;

// BookingAdapter.java
import android.app.DatePickerDialog;

import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;



// BookingAdapter.java
public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
    private Context context;
    private List<Booking> bookingList;

    public BookingAdapter(BookingsActivity context, List<Booking> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_row_booking, parent, false);
        return new ViewHolder(view);
    }

    // Update the onBindViewHolder method in BookingAdapter.java
    // Update the onBindViewHolder method in BookingAdapter.java
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.textViewName.setText(booking.getName());
        holder.textViewDetails.setText(String.format("%s | %s", booking.getDate(), booking.getInstructor()));

        // Edit functionality
        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the edit action here
                // For simplicity, let's show a DatePickerDialog for date editing
                showDatePickerDialog(context, booking);
            }
        });

        // Implement Delete functionality if required
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the booking from the database
                RoomDB.getInstance(context).bookingDao().delete(booking);

                // Update the list and notify the adapter
                bookingList.remove(booking);
                notifyDataSetChanged();
            }
        });
    }

    // Add a new method for showing the DatePickerDialog
    private void showDatePickerDialog(Context context, Booking booking) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Update the booking's date
                String newDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                booking.setDate(newDate);

                // Update the date in the database
                RoomDB.getInstance(context).bookingDao().updateDate(booking.getId(), newDate);

                // Notify the adapter that the data has changed
                notifyDataSetChanged();
            }
        };

        // Parse the existing date to get day, month, and year
        String[] dateParts = booking.getDate().split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1; // Month is 0-based
        int year = Integer.parseInt(dateParts[2]);

// Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);
        datePickerDialog.show();
    }



    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    // Update the ViewHolder class in BookingAdapter.java
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDetails; // Add this line
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDetails = itemView.findViewById(R.id.text_view_details); // Add this line
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }

}

