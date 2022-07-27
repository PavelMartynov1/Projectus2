package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.model.UserInfo;
import com.tradinghub.projectus2.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    public boolean saveUserInfo(UserInfo userInfo){
        userInfoRepository.save(userInfo);
        return true;
    }
}
