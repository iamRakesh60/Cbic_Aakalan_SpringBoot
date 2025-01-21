package com.Cbic_Aaklan_Project.Controller;

import com.Cbic_Aaklan_Project.Service.EmailService;
import com.Cbic_Aaklan_Project.Service.AdminService;
import com.Cbic_Aaklan_Project.entity.User;
import com.Cbic_Aaklan_Project.entity.UserEmail;
import com.Cbic_Aaklan_Project.payload.UserDTO;
import com.Cbic_Aaklan_Project.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cbicApi/api")
public class AdminController {

    private AdminService adminService;
    private EmailService emailService;
    public AdminController(AdminService adminService, EmailService emailService) {
        this.adminService = adminService;
        this.emailService = emailService;
    }

    @PostMapping("/registration")
    // http://localhost:8080/cbicApi/api/registration
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserDTO userDTO) {
        Map<String, String> response = new HashMap<>();
        String result = adminService.registerUser(userDTO);

        switch (result) {
            case "success":
                response.put("status", "success");
                response.put("message", "User registered successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            case "not_allowed":
                response.put("status", "failure");
                response.put("message", "Email not allowed for registration.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            case "already_registered":
                response.put("status", "failure");
                response.put("message", "User is already registered.");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);

            default:
                response.put("status", "failure");
                response.put("message", "Unexpected error.");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    // http://localhost:8080/cbicApi/api/login
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        Map<String, String> response = new HashMap<>();

        boolean isAuthenticated = adminService.authenticateUser(email, password);
        if (isAuthenticated) {
            String token = JwtUtil.generateToken(email); // Generate JWT Token
            response.put("status", "success");
            response.put("message", "Login successful.");
            response.put("token", token); // Add token to response
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid email or password.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
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
                adminService.updatePassword(email, newPassword);
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

        boolean isUpdated = adminService.updatePasswordIfOldMatches(email, oldPassword, newPassword);
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

    @PostMapping("/add-email")
    // http://localhost:8080/cbicApi/api/add-email
    public ResponseEntity<Map<String, String>> addEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String role = request.get("role");

        Map<String, String> response = new HashMap<>();
        boolean isAdded = adminService.addEmail(email, role);

        if (isAdded) {
            response.put("status", "success");
            response.put("message", "Email added successfully. Now You are eligible for Registration");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Email already exists.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    // email delete from only user email table
    @DeleteMapping("/delete-email")
    // http://localhost:8080/cbicApi/api/delete-email
    public ResponseEntity<Map<String, String>> deleteEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Map<String, String> response = new HashMap<>();
        boolean isDeleted = adminService.deleteEmailByEmail(email);

        if (isDeleted) {
            response.put("status", "success");
            response.put("message", "Email deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Email not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // email delete from both user email and user table
    @DeleteMapping("/delete-email-bothTable")
    // http://localhost:8080/cbicApi/api/delete-email-bothTable
    public ResponseEntity<Map<String, String>> deleteEmailFromBothtable(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Map<String, String> response = new HashMap<>();
        boolean isDeleted = adminService.deleteEmailFromBothTables(email);

        if (isDeleted) {
            response.put("status", "success");
            response.put("message", "Email deleted successfully from both tables.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "failure");
            response.put("message", "Email not found in either table.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // who is eligible for Registration
    @GetMapping("/approved-user")
    // http://localhost:8080/cbicApi/api/approved-user
    private ResponseEntity <List<UserEmail>> approvedUser(){
        List<UserEmail> approved_Email = adminService.approvedUser();
        return new ResponseEntity<>(approved_Email, HttpStatus.FOUND);
    }

    // Who is register after approval
    @GetMapping("/register-user")
    // http://localhost:8080/cbicApi/api/register-user
    public ResponseEntity<List<UserDTO>> getRegisteredUsers() {
        List<User> registeredUsers = adminService.getAllUsers();
        List<UserDTO> userDTOList = registeredUsers.stream()
                .map(user -> new UserDTO(user.getName(), user.getEmail(), null)) // Exclude password
                .collect(Collectors.toList());
        return new ResponseEntity<>(userDTOList, HttpStatus.FOUND);
    }
}