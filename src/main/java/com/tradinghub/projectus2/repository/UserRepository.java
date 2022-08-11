package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.AccountStatus;
import com.tradinghub.projectus2.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.verifyCode = ?1")
    User findByCode(String verifyCode);

}
