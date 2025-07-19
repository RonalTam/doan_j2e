package com.example.doan_web_j2e;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailUtil {
    public static void sendEmail(String toEmail, String subject, String content) throws MessagingException {
        final String fromEmail = "lakeheart001199@gmail.com"; // email gửi đi
        final String password = "myag hqjj lqkf jhzj"; // mật khẩu ứng dụng (không phải mật khẩu Gmail thường)

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromEmail));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        msg.setSubject(subject);
        msg.setContent(content, "text/html; charset=utf-8");

        Transport.send(msg);
    }
}
