package com.Cbic_Aaklan_Project.Service;

import com.Cbic_Aaklan_Project.entity.User;
import com.Cbic_Aaklan_Project.entity.UserEmail;
import com.Cbic_Aaklan_Project.repository.UserEmailRepository;
import com.Cbic_Aaklan_Project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {

    private UserRepository userRepository;
    private UserEmailRepository userEmailRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    public AdminService(UserRepository userRepository, UserEmailRepository userEmailRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.userEmailRepository = userEmailRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

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

    public boolean addEmail(String email, String role) {
        if (!userEmailRepository.existsByEmail(email)) {
            UserEmail userEmail = new UserEmail();
            userEmail.setEmail(email);
            userEmail.setRole(role);
            userEmailRepository.save(userEmail);
            return true;
        }
        return false;
    }

    // delete only from user email table
    public boolean deleteEmailByEmail(String email) {
        UserEmail userEmail = userEmailRepository.findByEmail(email);
        if (userEmail != null) {
            userEmailRepository.delete(userEmail);
            return true;
        }
        return false;
    }

    // delete both from user email and user table
    public boolean deleteEmailFromBothTables(String email) {
        boolean isDeletedFromUserEmail = false;
        boolean isDeletedFromUser = false;

        // Delete from UserEmail table if exists
        UserEmail userEmail = userEmailRepository.findByEmail(email);
        if (userEmail != null) {
            userEmailRepository.delete(userEmail);
            isDeletedFromUserEmail = true;
        }
        // Delete from User table if exists
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
            isDeletedFromUser = true;
        }
        // Return true if deletion happened in either or both tables
        return isDeletedFromUserEmail || isDeletedFromUser;
    }

    // who is eligible for Registration
    public List<UserEmail> approvedUser() {
        return userEmailRepository.findAll();
    }

    // Who is register after approval
    public List<User> registerUser() {
        return userRepository.findAll();
    }
}

