package com.example.countdown_app;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class CountdownActivity extends AppCompatActivity {

    private TextView textViewEventName;
    private TextView textViewTimer;
    private CountDownTimer countDownTimer;

    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        setTitle("Navigate");

        EventAdapter eventAdapter = new EventAdapter(eventList);
        List<Event> events = eventAdapter.getEvents(); // Get your list of events from somewhere

        // Get the parent layout where you want to add the event views
        ConstraintLayout parentLayout = findViewById(R.id.parentLayout); // Change this to match your actual parent layout

        if (events != null) {
            // Iterate over the list of events
            for (Event event : events) {
                // Inflate the layout for each event
                View eventView = getLayoutInflater().inflate(R.layout.activity_countdown, parentLayout, false);

                // Find and populate the TextView elements with event data
                TextView eventNameTextView = eventView.findViewById(R.id.textViewEventName);
                TextView timerTextView = eventView.findViewById(R.id.textViewTimer);
                eventNameTextView.setVisibility(View.VISIBLE);
                timerTextView.setVisibility(View.VISIBLE);

                eventNameTextView.setText(event.get_event_name());
                timerTextView.setText(String.valueOf(event.getTimeLeftInMillis()));

                // Add the inflated layout to the parent layout
                parentLayout.addView(eventView);
            }
        }
        else {
            TextView textNoEvents = findViewById(R.id.textNoEvents);
            textNoEvents.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
