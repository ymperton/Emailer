package com.company;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        final String receivingUsername = "dependencyinjectionhw@gmail.com";
        final String receivingPassword = "Moshe123";
//        final String receivingUsername = "pertonemailhomework@gmail.com";
//        final String receivingPassword = "Berachos64a!@#4";
        ReadMail rm = new ReadMail(new GoogleEmailReader());
        ArrayList<EmailMessage> listOfEmailsFromReceivalEmail = new ArrayList<>();

        //read all emails from first email.
        log.trace("Attempting to retrieve emails from email: " + receivingUsername);
        try {
            listOfEmailsFromReceivalEmail = rm.readMail(receivingUsername, receivingPassword);
            log.info("There are " + listOfEmailsFromReceivalEmail.size() + " emails.");
        } catch (Exception e) {
            String output = "";
            for (StackTraceElement s : e.getStackTrace()) {
                output += s + "\n";
            }
            log.error(output);
        }

        //send out all messages to second email.
        log.trace("Now sending out all emails");
        sendOutEmails(listOfEmailsFromReceivalEmail);


    }

    private static void sendOutEmails(ArrayList<EmailMessage> listOfEmailsFromReceivalEmail) {
        SendMail mt = new SendMail(new GoogleEmailSender());
        String emailToSendTo = "";
        String defaultSubjectMessage = "Automatic Forward";

        for (EmailMessage email : listOfEmailsFromReceivalEmail) {
            log.trace("Attempting to send email:\n" + email.toString());
            emailToSendTo = "ymperton@gmail.com";//email.getSubject();
            log.info("Sending it to " + emailToSendTo);
            if (isEmail(emailToSendTo)) {
                log.trace("It is a valid email");
                mt.sendMail(emailToSendTo, defaultSubjectMessage, email.getMessage());
            }
            log.trace(email);
        }
    }

    private static boolean isEmail(String emailToSendTo) {
        return (emailToSendTo.contains("@"));
    }

}
