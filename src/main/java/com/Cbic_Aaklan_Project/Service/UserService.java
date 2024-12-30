package com.Cbic_Aaklan_Project.Service;

import com.Cbic_Aaklan_Project.entity.User;
import com.Cbic_Aaklan_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
