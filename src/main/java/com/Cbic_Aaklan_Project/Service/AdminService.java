package com.Cbic_Aaklan_Project.Service;

import com.Cbic_Aaklan_Project.entity.User;
import com.Cbic_Aaklan_Project.repository.UserEmailRepository;
import com.Cbic_Aaklan_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEmailRepository userEmailRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    public String registerUser(User user) {
        // Check if the user is already registered
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "already_registered";
        }

        // Check if the email is allowed for registration
        if (userEmailRepository.existsByEmail(user.getEmail())) {
            user.setEmailVerivacation(true);
            user.setRegistrationTime(LocalDateTime.now());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            emailService.sendThankYouEmail(user.getEmail(), user.getName());
            return "success";
        }

        // Email not allowed for registration
        return "not_allowed";
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
    }

    public boolean updatePasswordIfOldMatches(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
