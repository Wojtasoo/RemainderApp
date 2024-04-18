package com.example.countdown_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AddEvent extends AppCompatActivity {
    private EditText eventNameEditText;
    private EditText eventDateTimeEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Navigate");

        // Initialize views
        eventNameEditText = findViewById(R.id.editTextEventName);
        eventDateTimeEditText = findViewById(R.id.editTextDate);
        addButton = findViewById(R.id.buttonSave);

        // Set click listener for the add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
    }

    private void addEvent() {
        String eventName = eventNameEditText.getText().toString();
        String eventDateTimeString = eventDateTimeEditText.getText().toString();

        // Validate inputs
        if (eventName.isEmpty() || eventDateTimeString.isEmpty()) {
            Toast.makeText(this, "Please enter event name and date/time", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse event date/time string to LocalDateTime
            LocalDateTime eventDateTime = LocalDateTime.parse(eventDateTimeString);

            // Convert LocalDateTime to Date
            Date eventDate = Date.from(eventDateTime.atZone(ZoneId.systemDefault()).toInstant());

            // Create Event object
            Event event = new Event(eventName, eventDate);

            // TODO: Save event to database or perform further actions

            // Display success message
            Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity and return to the previous screen
        } catch (Exception e) {
            // Handle parsing error
            Toast.makeText(this, "Invalid date/time format", Toast.LENGTH_SHORT).show();
        }
    }
}
