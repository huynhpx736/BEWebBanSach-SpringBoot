package com.example.bookshop.service;

import com.example.bookshop.payload.EmailDetails;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
    String sendMailToCustomer(Integer orderId, String subject);
}
