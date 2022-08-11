package com.tradinghub.projectus2.repository;


import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.model.user.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.verifyCode = ?1")
    User findByCode(String verifyCode);

    @Transactional
    @Modifying
    @Query("update User u set u.userInfo =:userInfo where u.id =:id")
    void updateUserInfo(@Param("userInfo") UserInfo userInfo,@Param("id") Long id);


}
