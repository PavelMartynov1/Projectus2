package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.controller.UserController;
import com.tradinghub.projectus2.model.Role;
import com.tradinghub.projectus2.model.User;
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
    public boolean verify(String verifyCode) {
        User user = userRepo.findByCode(verifyCode);
        logger.info("starting process of verifying "+verifyCode);
        logger.info("In DB user code "+user.getVerifyCode());

        if (user == null ) {
            return false;
        } else {
            user.setVerifyCode(null);
            user.setEnabled(true);
            userRepo.save(user);
            return true;
        }

    }
    public boolean save(User user){
        User userDB=userRepo.findByUsername(user.getUsername());
        if(userDB!=null){
            logger.warn("UserDB==null");
            return false;
        }
        List<Role> userRole = new ArrayList<>();
        userRole.add(new Role("ROLE_USER"));
        user.setRoles(userRole);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        String randomString= RandomString.make(64);
        user.setVerifyCode(randomString);
        logger.info("Saved new User "+user.getUsername());
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
    public void sendVerifyCode(User user) throws UnsupportedEncodingException, MessagingException
    {
        String toAddress = user.getEmail();
        String fromAddress = "martynovpasha0000@gmail.com";
        String senderName = "Your company name";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verifyURL ="https://tradinghub-01.herokuapp.com/verify?code=" + user.getVerifyCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }
}
