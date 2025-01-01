package com.Cbic_Aaklan_Project.Controller;

import com.Cbic_Aaklan_Project.Service.EmailService;
import com.Cbic_Aaklan_Project.Service.UserService;
import com.Cbic_Aaklan_Project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/registration")
    // http://localhost:8080/api/registration
    public Map<String, String> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            response.put("status", "success");
            response.put("message", "User registered successfully.");
        } else {
            response.put("status", "failure");
            response.put("message", "Email not allowed for registration.");
        }
        return response;
    }

    @PostMapping("/login")
    // http://localhost:8080/api/login
    public Map<String, String> loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        Map<String, String> response = new HashMap<>();

        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            response.put("status", "success");
            response.put("message", "Login successful.");
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid email or password.");
        }
        return response;
    }

    @PostMapping("/forget-password")
    // http://localhost:8080/api/forget-password
    public Map<String, String> forgetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        String newPassword = request.get("newPassword");

        Map<String, String> response = new HashMap<>();

        if (otp == null) {
            emailService.sendOtpEmail(email);
            response.put("status", "otp_sent");
            response.put("message", "OTP sent to your email.");
        } else {
            boolean isVerified = emailService.verifyOtp(email, otp);
            if (isVerified) {
                userService.updatePassword(email, newPassword);
                response.put("status", "success");
                response.put("message", "Password updated successfully.");
            } else {
                response.put("status", "failure");
                response.put("message", "Invalid OTP.");
            }
        }

        return response;
    }
}