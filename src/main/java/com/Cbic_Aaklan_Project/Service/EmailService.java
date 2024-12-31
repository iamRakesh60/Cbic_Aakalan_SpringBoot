package com.Cbic_Aaklan_Project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final Map<String, String> otpStore = new HashMap<>();

    public void sendOtpEmail(String email) {
        String otp = generateOtp();
        otpStore.put(email, otp);
        sendEmail(email, "OTP for Password Reset", "Your OTP is: " + otp);
    }

    public boolean verifyOtp(String email, String otp) {
        return otp.equals(otpStore.get(email));
    }

    private String generateOtp() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iamrakeshit273@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}