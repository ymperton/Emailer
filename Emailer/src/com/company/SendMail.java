package com.company;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

interface IMailer {
    void sendMail(String to, String subject, String message);
}

class SendMail {
    private IMailer mail;
    private String to, subject, message;

    public SendMail(IMailer mail) {
        this.mail = mail;
    }

    public void sendMail(String to, String subject, String message) {
        mail.sendMail(to, subject, message);
    }
}

class GoogleEmailSender implements IMailer {
    private static Logger log = Logger.getLogger(String.valueOf(GoogleEmailSender.class));
    @Override
    public void sendMail(String to, String subject, String message) {
        log.info("Starting to send the message");
        //the sending email
        final String username = "ahronmannsending@gmail.com";
        final String password = "Bera64chos@";
        log.trace("Sending it from email " + username);

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
//            log.info("Attempting to send a message.");

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);

            log.info("Message Sent succesfully.");

        } catch (MessagingException e) {
            log.error("There has been an error sending the message.");
            throw new RuntimeException(e);
        }
    }
}
