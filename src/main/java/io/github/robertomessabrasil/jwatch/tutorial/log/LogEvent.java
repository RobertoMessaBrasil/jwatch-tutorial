package io.github.robertomessabrasil.jwatch.tutorial.log;

import io.github.robertomessabrasil.jwatch.observer.listener.Event;

public class LogEvent extends Event {

    private String message;

    public String getMessage() {
        return message;
    }

    public LogEvent setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "LogEvent: " + this.getMessage();
    }
}
