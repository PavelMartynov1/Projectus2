package com.tradinghub.projectus2.controller.user;

import com.tradinghub.projectus2.errorExeptions.PasswordException;
import com.tradinghub.projectus2.errorExeptions.UserAlreadyExistException;
import com.tradinghub.projectus2.model.account.Account;

import com.tradinghub.projectus2.model.dto.user.UserDTO;
import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.model.user.UserInfo;
import com.tradinghub.projectus2.service.AccountService;
import com.tradinghub.projectus2.service.UserInfoService;
import com.tradinghub.projectus2.service.UserService;
import com.tradinghub.projectus2.utils.pageHelper.ProfilePageHelper;
import com.tradinghub.projectus2.utils.sort.profileSort.ProfileSortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
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
    AccountService accountService;
    @Autowired
    ProfilePageHelper helper;

    /*
     Serves user registration template
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "user/registration-test";
    }

    /*
     Registering a new user
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute("user")
                                  @Valid UserDTO userForm,
                                  BindingResult result, Model model) {

        if(result.hasErrors()){
            return "user/registration-test";
        }
        try {
            userService.save(userForm.build());
        } catch (UserAlreadyExistException e) {
            model.addAttribute("Username_is_taken", e);
            return "redirect:/login";
        }
        return "redirect:/login";
    }
    /*
    Log in page
     */
    @RequestMapping(value = "/login")
    public String getLoginPage(){
        return "user/login.html";
    }
    /*
    load user picture
     */
    @PostMapping("user/image")
    public String setProfilePic(@RequestParam("image") MultipartFile image,
                                Principal principal) {
        try {
            userService.setUserPicture(principal.getName(),image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/profile";

    }
/*
user code verify
 */
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    /*
    user cabinet page
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePage(@RequestParam(defaultValue = "1", name = "pageNo") Integer pageNo,
                              @RequestParam(defaultValue = "3", name = "pageSize") Integer pageSize,
                              @RequestParam(required = false,name = "accountStatus") String accountStatus,
                              @RequestParam(required = false,name = "lookUpsOrder") String lookUpsOrder,
                              @RequestParam(required = false) Boolean drop,
                              Model model, Principal principal,
                              HttpSession session) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        Set<Account> userAccounts = userService.getUserAccounts(principal.getName());
        model.addAttribute("accounts", userAccounts);
        ProfileSortParams sortParams = ProfileSortParams.newBuilder()
                .setPageNo(pageNo)
                .setSize(pageSize)
                .setAccountStatus(accountStatus)
                .setUserId(user.getId())
                .setLookUpsOrder(lookUpsOrder)
                .build();
        logger.warn(sortParams.toString());
        Page<Account > page=helper.getPage(sortParams,session);
        if(page==null){
            logger.info("page is null");
            return "redirect:/";
        }
        model.addAttribute("accounts",page.getContent());
        model.addAttribute("current_page",page.getNumber()+1);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("pageSize",pageSize);
        return "user/profile.html";
    }
/*
change user password
 */
    @RequestMapping(value = "/profile/change-password", method = RequestMethod.POST)
    public String changeUserPassword(@RequestParam String newPassword,
                                     @RequestParam String password,
                                     Principal principal, Model model) {
        try {
            userService.changeUserPassword
                    (principal.getName(), password, newPassword);
        } catch (PasswordException e) {
            model.addAttribute("password", e.getMessage());
        }
        return "redirect:/login";
    }
    /*
    change user email
    need to implement email verify
     */
    @RequestMapping(value = "/profile/change-email", method = RequestMethod.POST)
    public String changeUserEmail(@RequestParam String newEmail,
                                  Principal principal, Model model) {
        userService.changeUserEmail(principal.getName(), newEmail);
        return "redirect:/login";
    }
    @GetMapping("/profile/user_info")
    public ModelAndView getProfileInfo(ModelAndView modelAndView) {
        modelAndView.addObject("userInfo",
                new UserInfo());
        modelAndView.setViewName("user/user_info_form");
        return modelAndView;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "redirect:/home-test";
    }

}
