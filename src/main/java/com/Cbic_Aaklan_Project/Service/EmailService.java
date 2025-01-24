package com.Cbic_Aaklan_Project.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private static final int OTP_EXPIRATION_MINUTES = 10;

    // Store OTP and its generation time
    private final Map<String, OtpDetails> otpStore = new HashMap<>();

    public void sendOtpEmail(String email) {
        String otp = generateOtp();
        otpStore.put(email, new OtpDetails(otp, LocalDateTime.now()));
        sendEmail(email, "Email Verification Code for AAKLAN DASHBOARD",
                "To verify your email address, please use the following One-Time Password (OTP): " + otp +
                        "\n\nThis OTP is valid for the next 30 minutes. Please do not share it with anyone.\n" +
                        "If you did not initiate this request, please contact our support team immediately.\n\n" +
                        "Best regards,\nThe Aaklan Team");
    }

    public boolean verifyOtp(String email, String otp) {
        OtpDetails otpDetails = otpStore.get(email);
        if (otpDetails == null) {
            return false; // No OTP found for the email
        }

        // Check if OTP is expired
        if (isOtpExpired(otpDetails.getTimestamp())) {
            otpStore.remove(email); // Remove expired OTP
            return false;
        }

        // Validate OTP
        boolean isValid = otp.equals(otpDetails.getOtp());
        if (isValid) {
            otpStore.remove(email); // Remove OTP after successful verification
        }
        return isValid;
    }

    private boolean isOtpExpired(LocalDateTime timestamp) {
        return timestamp.plusMinutes(OTP_EXPIRATION_MINUTES).isBefore(LocalDateTime.now());
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

    // Inner class to store OTP and its timestamp
    private static class OtpDetails {
        private final String otp;
        private final LocalDateTime timestamp;

        public OtpDetails(String otp, LocalDateTime timestamp) {
            this.otp = otp;
            this.timestamp = timestamp;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
