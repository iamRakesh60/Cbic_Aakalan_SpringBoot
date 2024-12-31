package com.Cbic_Aaklan_Project.repository;

import com.Cbic_Aaklan_Project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}