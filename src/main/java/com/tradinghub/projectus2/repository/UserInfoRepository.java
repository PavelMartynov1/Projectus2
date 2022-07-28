package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
