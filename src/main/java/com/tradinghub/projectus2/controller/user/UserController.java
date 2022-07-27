package com.tradinghub.projectus2.controller.user;

import com.tradinghub.projectus2.errorExeptions.PasswordException;
import com.tradinghub.projectus2.errorExeptions.UserAlreadyExistException;
import com.tradinghub.projectus2.model.ItemDetails.ItemDetails;
import com.tradinghub.projectus2.model.User;
import com.tradinghub.projectus2.model.UserInfo;
import com.tradinghub.projectus2.model.enums.Currency;
import com.tradinghub.projectus2.model.enums.ItemEnum;
import com.tradinghub.projectus2.model.instagram.InstItem;
import com.tradinghub.projectus2.service.InstService;
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

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private InstService instService;
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value="/sell_item",method=RequestMethod.GET)
    public String getSellItemPage(){
        return "user/sell_account.html";
    }
    @RequestMapping(value="/sell_item",method=RequestMethod.POST)
    public String add_item(@RequestParam(name ="category") String category,
                           @RequestParam(name ="header") String header,
                           @RequestParam(name ="text") String text,
                           @RequestParam(name ="email:password") String email_password,
                           @RequestParam(name ="url") String url,
                           @RequestParam(name ="price") String price,
                           @RequestParam(name ="currency") String currency,
                           @RequestParam(name ="code",required = false) String code,
                            Model model){
        String[] emailPassword=email_password.split(":");
        ItemDetails details=new ItemDetails();
        InstItem inst=new InstItem();
        inst.setPassword(emailPassword[1]);
        inst.setPassword(emailPassword[0]);
        inst.setCurrency(Currency.valueOf(currency));
        inst.setItemEnum(ItemEnum.valueOf(category));
        inst.setPrice(price);
        inst.setItemDetails(details);
        details.setFollowers(String.valueOf(111));
        details.setHeader(header);
        details.setUrl(url);
        details.setText(text);
        details.setItem(inst);

        instService.saveInstAccount(inst);
        return "user/sell_account.html";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerNewUser(@ModelAttribute("user")
                                  @Valid User userForm,
                                  BindingResult result,Model model) {
        logger.info("loh");
        try {
            userService.save(userForm);
        } catch (UserAlreadyExistException e){
            model.addAttribute("Username_is_taken",e);
            return "redirect:/login";
        }
            return "redirect:/login";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            logger.info("User verified");
            return "verify_success";
        } else {
            logger.info("verify_fail");
            return "verify_fail";
        }
    }

    @GetMapping("/profile")
    public String loginSuccess(Model model, Principal principal) {
        User user=userService.findUserByUsername(principal.getName());
        model.addAttribute("user",user);
        model.addAttribute("userInfo",
                new UserInfo());
        return "user/User-Cabinet.html";
    }
    @GetMapping("/profile/user_info")
    public ModelAndView getProfileInfo(ModelAndView modelAndView){
        modelAndView.addObject("userInfo",
                new UserInfo());
        modelAndView.setViewName("user/user_info_form");
        return  modelAndView;
    }
    @RequestMapping(value = "/profile/change-password",method = RequestMethod.POST)
    public String changeUserPassword(@RequestParam String newPassword,
                                     @RequestParam String password,
            Principal principal,Model model){
        try{
            userService.changeUserPassword
                    (principal.getName(),password,newPassword);
        } catch (PasswordException e) {
            model.addAttribute("password",e.getMessage());
            logger.info("passwordException");
        }
        return "redirect:/login";
    }
    @RequestMapping(value ="/profile/change-email",method=RequestMethod.POST )
    public String changeUserEmail(@RequestParam String newEmail,
                                  Principal principal,Model model){
        userService.changeUserEmail(principal.getName(),newEmail);
        return "redirect:/login";
    }
    @RequestMapping(value = "/profile/user_info",method = RequestMethod.POST)
    public String setProfileInfo(@ModelAttribute("userInfo")
                                           @Valid UserInfo userInfo,
                                 Principal principal){
        User user=userService.findUserByUsername(principal.getName());
        userInfo.setUser(user);
        user.setUserInfo(userInfo);
        userService.setUserInfo(user);
        userInfoService.saveUserInfo(userInfo);
        logger.info(userInfo.getFirstName());
        logger.info(userInfo.getLastName());
        logger.info(userInfo.getTg());
        logger.info(userInfo.getNumber());
        return "redirect:/profile";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied.html";
    }


    @RequestMapping(value = "/",method=RequestMethod.GET)
    public String getIndexPage() {
       return "redirect:/home";
    }

}
