package com.tradinghub.projectus2.controller.admin;

import com.tradinghub.projectus2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/users",method= RequestMethod.GET)
    public ModelAndView usersList(ModelAndView model){
        model.addObject("users",userService.getAllUsers());
        model.setViewName("users_list");
        return model;
    }

}
