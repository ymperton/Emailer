package com.company;

import java.util.Date;

public class EmailMessage {
    private String subject, from, to, message;
    private Date dateReceived;

    public EmailMessage(String subject, String from, String to, String message, Date dateReceived) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.message = message;
        this.dateReceived = dateReceived;
    }

    @Override
    public String toString() {
        return String.format("Date: %s\nSubject: %s\nFrom: %s\nTo: %s\nText: %s",
                dateReceived, subject, from, to, message);
    }

    public String getSubject() {
        return subject;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateReceived() {
        return dateReceived;
    }
}
