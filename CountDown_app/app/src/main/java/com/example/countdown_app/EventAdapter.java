package com.example.countdown_app;

import android.view.LayoutInflater;
import android.view.View;

import java.util.Locale;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.os.CountDownTimer;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events;

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_countdown, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.textViewEventName.setText(event.get_event_name());

        // Start a countdown timer for this event and update the TextView
        long countdownMillis = event.getTimeLeftInMillis();
        new CountDownTimer(countdownMillis, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;

                String timerText = String.format(Locale.getDefault(),"%02d:%02d:%02d",
                        hours % 24,
                        minutes % 60,
                        seconds % 60);
                holder.textViewTimer.setText(timerText);
            }

            public void onFinish() {
                holder.textViewEventName.setText(R.string.event_name);
            }
        }.start();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public List<Event> getEvents()
    {
        return events;
    }
    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEventName;
        TextView textViewTimer;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEventName = itemView.findViewById(R.id.textViewEventName);
            textViewTimer = itemView.findViewById(R.id.textViewTimer);
        }
    }
}

