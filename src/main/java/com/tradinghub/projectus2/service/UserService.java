package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.controller.UserController;
import com.tradinghub.projectus2.model.Role;
import com.tradinghub.projectus2.model.User;
import com.tradinghub.projectus2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean save(User user){
        User userDB=userRepo.findByUsername(user.getUsername());
        if(userDB!=null){
            logger.warn("UserDB==null");
            return false;
        }

        user.setRoles(Collections.singleton(new Role("USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        logger.info("Saved new User "+user.getUsername());
        return true;
    }
}
