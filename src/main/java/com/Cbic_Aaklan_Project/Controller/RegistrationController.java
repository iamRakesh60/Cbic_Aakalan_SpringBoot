package com.Cbic_Aaklan_Project.Controller;

import com.Cbic_Aaklan_Project.Service.EmailService;
import com.Cbic_Aaklan_Project.Service.UserService;
import com.Cbic_Aaklan_Project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
// http://localhost:8080/api/registration
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/registration")
    public Map<String,String> registerUser(@RequestBody User user){

        // Register user without email verification
        User registeredUser = userService.registerUser(user);

        // Send OTP email for email verification
        emailService.sendOtpEmail(user.getEmail());

        return null;
    }
}
