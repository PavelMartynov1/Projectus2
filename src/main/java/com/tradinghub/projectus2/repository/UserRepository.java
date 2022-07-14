package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.verifyCode = ?1")
    User findByCode(String verifyCode);
}
