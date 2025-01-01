package com.Cbic_Aaklan_Project.Service;

import com.Cbic_Aaklan_Project.entity.User;
import com.Cbic_Aaklan_Project.repository.UserEmailRepository;
import com.Cbic_Aaklan_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEmailRepository userEmailRepository;

    public boolean registerUser(User user) {
        if (userEmailRepository.existsByEmail(user.getEmail())) {
            user.setEmailVerivacation(true);
            user.setRegistrationTime(LocalDateTime.now());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }
}