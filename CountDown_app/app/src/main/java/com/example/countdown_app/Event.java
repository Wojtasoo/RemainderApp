package com.example.countdown_app;

import java.util.Date;
import java.time.Duration;
import java.time.Instant;


public class Event {
    private String event_name;
    private Date date;

    public Event(String input_name,Date input_date)
    {
        this.event_name=input_name;
        this.date=input_date;
    }

    public String get_event_name()
    {
        return event_name;
    }
    public Date get_event_date()
    {
        return date;
    }
    public long getTimeLeftInMillis() {
        Instant now = Instant.now();
        Duration duration = Duration.between(now, date.toInstant());

        return duration.toMillis();
    }
}
