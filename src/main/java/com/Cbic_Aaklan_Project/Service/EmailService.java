package com.Cbic_Aaklan_Project.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private final Map<String, String> otpStore = new HashMap<>();

    public void sendOtpEmail(String email) {
        String otp = generateOtp();
        otpStore.put(email, otp);
        sendEmail(email, "Email Verification Code for AAKLAN DASHBOARD",
                "To verify your email address, please use the following One-Time Password (OTP): " + otp  +
                        "                        \"\\n This OTP is valid for the next 10 minutes. Please do not share it with anyone.\\n\" +\n" +
                        "                        \"If you did not initiate this request, please contact our support team immediately.\\n\" +\n" +
                        "                        \"Best regards,\\u2028The Aaklan Team");
    }

    public boolean verifyOtp(String email, String otp) {
        return otp.equals(otpStore.get(email));
    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    public void sendThankYouEmail(String email, String name) {
        String subject = "Welcome to DGMP Aakalan!";
        String message = String.format(
                "Hello %s,\n\nThank you for registering with AAKALAN DASHBOARD.\nWe are delighted to have you on board.\n\nBest Regards,\nThe Aakalan Technical Team",
                name
        );
        sendEmail(email, subject, message);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dgpmaakalan@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}