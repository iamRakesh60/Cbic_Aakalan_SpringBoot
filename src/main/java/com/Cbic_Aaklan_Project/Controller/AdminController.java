package com.Cbic_Aaklan_Project.Controller;

import com.Cbic_Aaklan_Project.Service.EmailService;
import com.Cbic_Aaklan_Project.Service.UserService;
import com.Cbic_Aaklan_Project.entity.User;
import com.Cbic_Aaklan_Project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cbicApi/api")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/registration")
    // http://localhost:8080/cbicApi/api/registration
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        boolean isRegistered = userService.registerUser(user);

        if (isRegistered) {
            response.put("status", "success");
            response.put("message", "User registered successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Email not allowed for registration.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    // http://localhost:8080/cbicApi/api/login
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        Map<String, String> response = new HashMap<>();

        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            String token = JwtUtil.generateToken(email); // Generate JWT Token
            response.put("status", "success");
            response.put("message", "Login successful.");
            response.put("token", token); // Add token to response
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @PostMapping("/forget-password")
    // http://localhost:8080/cbicApi/api/forget-password
    public ResponseEntity<Map<String, String>> forgetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        String newPassword = request.get("newPassword");

        Map<String, String> response = new HashMap<>();

        if (otp == null) {
            emailService.sendOtpEmail(email);
            response.put("status", "otp_sent");
            response.put("message", "OTP sent to your email.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            boolean isVerified = emailService.verifyOtp(email, otp);
            if (isVerified) {
                userService.updatePassword(email, newPassword);
                response.put("status", "success");
                response.put("message", "Password updated successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "failure");
                response.put("message", "Invalid OTP.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/update-password")
    // http://localhost:8080/cbicApi/api/update-password
    public ResponseEntity<Map<String, String>> updatePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");

        Map<String, String> response = new HashMap<>();

        boolean isUpdated = userService.updatePasswordIfOldMatches(email, oldPassword, newPassword);
        if (isUpdated) {
            response.put("status", "success");
            response.put("message", "Password updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid email or old password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}