package com.tradinghub.projectus2.controller.instagram;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.instagram.IgUser;
import com.tradinghub.projectus2.model.dto.account.InstagramDTO;
import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.service.AccountService;
import com.tradinghub.projectus2.service.UserService;
import com.tradinghub.projectus2.utils.ContentHelper;
import com.tradinghub.projectus2.utils.sort.homeSort.HomeSortHelper;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class InstagramController {
    @Autowired
    HomeSortHelper homeSortHelper;
    @Autowired
    AccountService accountService;
    @Autowired
    ContentHelper list_helper;
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(InstagramController.class);


    @RequestMapping(value = "/inst/sell_account", method = RequestMethod.GET)
    public ModelAndView sellAccountPage(ModelAndView model,HttpSession session) {
        String code=RandomString.make(8);
        session.setAttribute("code",code);
        model.addObject("code", code);
        model.addObject("account", new InstagramDTO());
        model.setViewName("instagram/add_inst_account.html");
        return model;
    }

    @RequestMapping(value = "/inst/sell_account", method = RequestMethod.POST)
    public String addAccount(@ModelAttribute("account") @Valid InstagramDTO account,
                             BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            logger.info("Errors were found");
            List<ObjectError> errors = bindingResult.getAllErrors();
            return "instagram/add_inst_account.html";

        }
        /*
        Implement verify process
         */
        User user = userService.findUserByUsername(principal.getName());
        accountService.saveAccount(account.buildAccount(user));
        return "redirect:/profile";
    }

    /*
    Scraping instagram account metaInfo
     */
    @RequestMapping(value = "/instagram_info", method = RequestMethod.GET)
    public ModelAndView getInstaInfoPage(ModelAndView model) {

        /*
        Takes very long time to process a request
         */
//        Scanner scanner = new Scanner(System.in);
//        Callable<String> inputCode = () -> {
//            System.out.print("Please input code: ");
//            return scanner.nextLine();
//        };
//
//// handler for challenge login
//        IGClient.Builder.LoginHandler challengeHandler = (client, response) -> {
//            // included utility to resolve challenges
//            // may specify retries. default is 3
//            return IGChallengeUtils.resolveChallenge(client, response, inputCode);
//        };
//
//        IGClient client = IGClient.builder()
//                .username("martynovpasha0000@gmail.com")
//                .password("dpfBHf2U")
//                .onChallenge(challengeHandler)
//                .login();
//        CompletableFuture<UserAction> usersSearchResponse=
//                client.getActions().users().findByUsername("thehughjackman");
//        logger.info(usersSearchResponse.get().getUser().getBiography());
        /*
        Uses fb api
         */
        String parameters = "business_discovery.username(bluebottle){followers_count,media_count,biography}";
        FacebookClient client = new DefaultFacebookClient("EAAIixFooZAqQBABRyyifK9I1kQfBuE83eX91KbZARZBLRHWqrx4ZBiUdXOMYNYjiv5KnOBLWXnc1JOO9ZC6Oo4vR6jbMK1vW5QKfgzZCoh7ZAYRZCnFnMfmZBWYYIoCkZApZBoTud3sOIU3sm7ls4H6XRZBhdpK2OM3HRbsVfHhUhbaQaXtgIG4BG0QgAPPsjHp4wRtZCYCq2rz4XgmgyghzLVjtLmWNI0kUMCLQZD", Version.VERSION_7_0);
        IgUser igProfile = client.fetchObject("17841403153343619", IgUser.class,
                Parameter.with("fields", parameters));
        IgUser profileInfo = igProfile.getBusinessDiscovery();
        igProfile.getMedia();
        model.setViewName("instagram_info");
        model.addObject("accountInfo", profileInfo.getBiography());
        return model;
    }
}
