package com.atalaykaan.tasktracker.tasktrackerproject.exception;

import java.time.LocalDate;

public class ExceptionDetails {

    private LocalDate timeStamp;
    private String message;
    private String description;

    public ExceptionDetails(LocalDate timeStamp, String message, String description) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.description = description;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
