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

    public boolean registerUser(User user) {
        if (userEmailRepository.existsByEmail(user.getEmail())) {
            user.setEmailVerivacation(true);
            user.setRegistrationTime(LocalDateTime.now());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            emailService.sendThankYouEmail(user.getEmail(), user.getName());

            return true;
        }
        return false;
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
