package com.Cbic_Aaklan_Project.repository;

import com.Cbic_Aaklan_Project.entity.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {
    boolean existsByEmail(String email);
}