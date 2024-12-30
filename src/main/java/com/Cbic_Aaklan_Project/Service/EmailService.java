package com.Cbic_Aaklan_Project.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.Cbic_Aaklan_Project.Service.EmailVerificationService.emailOtpMapping;


@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    private final UserService userService;

    public EmailService(UserService userService, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }


    public  String generateOtp(){
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }


    public void sendOtpEmail(String email) {
        String otp = generateOtp();
        System.out.println(otp);

        // save otp for leter verification
        emailOtpMapping.put(email,otp);

        sendEmail(email, "Email Verification Code for Aaklan Project",
                "Thank you for registering with Aaklan. To verify your email address, please use the following One-Time Password (OTP): " + otp +
                        "\n This OTP is valid for the next 10 minutes. Please do not share it with anyone.\n" +
                        "If you did not initiate this request, please contact our support team immediately.\n" +
                        "Best regards,\u2028The Aaklan Team");
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("iamrakesh273@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
