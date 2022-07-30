package com.tradinghub.projectus2.controller.user;

import com.tradinghub.projectus2.errorExeptions.PasswordException;
import com.tradinghub.projectus2.errorExeptions.UserAlreadyExistException;
import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.dto.account.AccountDTO;
import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.model.user.UserInfo;
import com.tradinghub.projectus2.service.AccountService;
import com.tradinghub.projectus2.service.UserInfoService;
import com.tradinghub.projectus2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AccountService accountService;

    /*
     Serves user registration template
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(Model model) {
        model.addAttribute("user", new User());
        return "user/registration";
    }

    /*
     Registering a new user
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute("user")
                                  @Valid User userForm,
                                  BindingResult result, Model model) {
        try {
            userService.save(userForm);
        } catch (UserAlreadyExistException e) {
            model.addAttribute("Username_is_taken", e);
            return "redirect:/login";
        }
        return "redirect:/login";
    }

    /*
     Serves selling  page
     */
    @RequestMapping(value = "user/sell_item", method = RequestMethod.GET)
    public String getSellItemPage(Model model) {
        model.addAttribute("account",new AccountDTO());
        return "user/sell_account.html";
    }

    /*
     Saves account for sale
     */
    @RequestMapping(value = "user/sell_item", method = RequestMethod.POST)
    public String add_item(@ModelAttribute("account") @Valid AccountDTO account,
                           BindingResult bindingResult,Principal principal) {
        if(bindingResult.hasErrors()){
            return "user/sell_account.html";
        }
        User user=userService.findUserByUsername(principal.getName());
        accountService.saveAccount(account.build(user));
        return "redirect:/profile";
    }


    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
           // logger.info("User verified");
            return "verify_success";
        } else {
           // logger.info("verify_fail");
            return "verify_fail";
        }
    }

    @GetMapping("/profile")
    public String loginSuccess(Model model, Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userInfo",
                new UserInfo());
        Set<Account> userAccounts=userService.getUserAccounts(principal.getName());
        model.addAttribute("accounts",userAccounts);
        return "user/user-cabinet.html";
    }

    @GetMapping("/profile/user_info")
    public ModelAndView getProfileInfo(ModelAndView modelAndView) {
        modelAndView.addObject("userInfo",
                new UserInfo());
        modelAndView.setViewName("user/user_info_form");
        return modelAndView;
    }

    @RequestMapping(value = "/profile/change-password", method = RequestMethod.POST)
    public String changeUserPassword(@RequestParam String newPassword,
                                     @RequestParam String password,
                                     Principal principal, Model model) {
        try {
            userService.changeUserPassword
                    (principal.getName(), password, newPassword);
        } catch (PasswordException e) {
            model.addAttribute("password", e.getMessage());
           // logger.info("passwordException");
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/profile/change-email", method = RequestMethod.POST)
    public String changeUserEmail(@RequestParam String newEmail,
                                  Principal principal, Model model) {
        userService.changeUserEmail(principal.getName(), newEmail);
        return "redirect:/login";
    }

    @RequestMapping(value = "/profile/user_info", method = RequestMethod.POST)
    public String setProfileInfo(@ModelAttribute("userInfo")
                                 @Valid UserInfo userInfo,
                                 Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        userInfo.setUser(user);
        user.setUserInfo(userInfo);
        userService.setUserInfo(user);
        userInfoService.saveUserInfo(userInfo);
        return "redirect:/profile";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied.html";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "redirect:/home";
    }

}
