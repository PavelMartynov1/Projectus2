package com.tradinghub.projectus2.controller;

import com.tradinghub.projectus2.model.User;
import com.tradinghub.projectus2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value="/registration",method= RequestMethod.GET)
    public String getRegPage(Model model){
        logger.info("Request for reg page");
        model.addAttribute("user",new User());
        return "registration";
    }
    @RequestMapping(value="/registration",method= RequestMethod.POST)
    public String registerNewUser(@ModelAttribute("user")
                                      @Valid User userForm,
                                  BindingResult bindingResult, Model model ){
        userService.save(userForm);
        return "register_success";
    }
    @GetMapping("/login_success")
    public String listUsers() {
        return "login_success.html";
    }
    @RequestMapping(value = "/")
    public String getIndexPage(){

        logger.info("Request for index page");
        return "index";
    }
}
