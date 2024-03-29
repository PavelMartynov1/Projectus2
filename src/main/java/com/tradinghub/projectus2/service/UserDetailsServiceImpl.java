package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(),
                user.getPassword(), user.getCustomUserDetails().isEnabled(),
                user.getCustomUserDetails().isAccountNonExpired(),
                user.getCustomUserDetails().isCredentialsNonExpired(),
                user.getCustomUserDetails().isAccountNonLocked(),
                user.getCustomUserDetails().getAuthorities());
    }
}
