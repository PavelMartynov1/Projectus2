package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.errorExeptions.UserAlreadyExistException;
import com.tradinghub.projectus2.model.Role;
import com.tradinghub.projectus2.model.user.CustomUserDetails;
import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.model.user.UserInfo;
import com.tradinghub.projectus2.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public User findUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
    public boolean verify(String verifyCode) {
        User user = userRepo.findByCode(verifyCode);
       // logger.info("starting process of verifying " + verifyCode);
      //  logger.info("In DB user code " + user.getVerifyCode());

        user.setVerifyCode(null);
        user.getCustomUserDetails().setEnabled(true);
        userRepo.save(user);
        return true;

    }
    public void changeUserPassword(String username,String password,String newPassword){
            User user=userRepo.findByUsername(username);
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepo.save(user);

    }
    public void changeUserEmail(String username,String newEmail){
        User user=findUserByUsername(username);
        user.setEmail(newEmail);
        try {
            sendVerifyCode(user);
            userRepo.save(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public boolean checkValidPassword(String password,User user){
        String encodedPassword=bCryptPasswordEncoder.encode(password);
      //  logger.info(encodedPassword);
       // logger.info(user.getPassword());
        return encodedPassword.equals(user.getPassword());
    }
    public void setUserInfo(User user){
        userRepo.save(user);
    }
    public boolean save(User user) {
        User userDB = userRepo.findByUsername(user.getUsername());
        if (userDB != null) {
            throw new UserAlreadyExistException("Username is already taken");
        }
        List<Role> userRole = new ArrayList<>();
        userRole.add(new Role("ROLE_USER"));
        user.setRoles(userRole);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCustomUserDetails(new CustomUserDetails());
        user.setUserInfo(new UserInfo());
        user.getCustomUserDetails().setEnabled(false);
        user.getCustomUserDetails().setAccountNonExpired(true);
        user.getCustomUserDetails().setAccountNonLocked(true);
        user.getCustomUserDetails().setCredentialsNonExpired(true);
        String randomString = RandomString.make(64);
        user.setVerifyCode(randomString);
       // logger.info("Saved new User " + user.getUsername());
        userRepo.save(user);

        try {
            sendVerifyCode(user);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void sendVerifyCode(User user) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "martynovpasha0000@gmail.com";
        String senderName = "TradingHub";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + senderName;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = "https://tradinghub-01.herokuapp.com/verify?code=" + user.getVerifyCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
