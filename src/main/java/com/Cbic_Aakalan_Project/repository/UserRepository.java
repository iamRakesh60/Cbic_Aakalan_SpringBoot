package com.Cbic_Aakalan_Project.repository;

import com.Cbic_Aakalan_Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}