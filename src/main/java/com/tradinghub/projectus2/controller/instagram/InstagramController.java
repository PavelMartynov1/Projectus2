package com.tradinghub.projectus2.controller.instagram;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.instagram.IgUser;
import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.service.AccountService;
import com.tradinghub.projectus2.utils.ContentHelper;
import com.tradinghub.projectus2.utils.SortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller

public class InstagramController {
    @Autowired
    SortHelper sortHelper;
    @Autowired
    AccountService accountService;
    @Autowired
    ContentHelper list_helper;

    Logger logger = LoggerFactory.getLogger(InstagramController.class);

    @RequestMapping(value = "/inst", method = RequestMethod.GET)
    public String getInstagramAccounts(@RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "5") Integer pageSize,
                                       @RequestParam(required = false) String price,
                                       @RequestParam(required = false) String followers,
                                       @RequestParam(required = false) Boolean drop,
                                       Model model, HttpSession session) {


        Page<Account> page;
        Optional<Sort> sortParams =sortHelper.getSort(price,followers);
        if (sortParams.isPresent()) {
            page = accountService
                    .findPaginatedByCategoryWithSort(AccountCategory.inst,pageNo - 1, pageSize, sortParams.get());
            session.setAttribute("sortParams", sortParams.get());
        } else {
            Sort session_sort = (Sort) session.getAttribute("sortParams");
            if (session_sort != null) {
                page = accountService.findPaginatedByCategoryWithSort(AccountCategory.inst,pageNo - 1,
                        pageSize,
                        session_sort);
            } else {
                page = accountService.findPaginatedByCategory(AccountCategory.inst,pageNo - 1, pageSize);
            }
        }

        List<Account[]> list =list_helper.getList(page.getContent(),4);
        model.addAttribute("items",list);
        model.addAttribute("current_page",page.getNumber()+1);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize",pageSize);
        return "instagram/instagram.html";
    }

    @RequestMapping(value = "inst/item", method = RequestMethod.GET)
    public String getAccountInfoPage(@RequestParam(required = true, name = "id")
                                             String id,
                                     Model model) {

        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            model.addAttribute("account", account.get());
        } else {
            return "redirect:/home";
        }

        return "instagram/inst_account.html";
    }

    @RequestMapping(value = "/accounts_for_sale", method = RequestMethod.GET)
    public String getAccountsPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  @RequestParam(required = false) String price,
                                  @RequestParam(required = false) String followers,
                                  @RequestParam(required = false) Boolean drop,
                                  Model model, HttpSession session) {
        if (drop != null) {
            if (drop) {
                session.removeAttribute("sortParams");
            }
        }
        /*
        ПЕРЕПИСАТЬ
         */

//        Page<Product> page;
//        Optional<Sort> sortParams = sortHelper.getSort(price, followers);
//        if (sortParams.isPresent()) {
//            //logger.info("sort params are present");
//            page = productService.findPaginatedWithSort(pageNo - 1,
//                    pageSize,
//                    sortParams.get());
//            session.setAttribute("sortParams", sortParams.get());
//        } else {
//            //logger.info("sort params are not present and are taken from session");
//            Sort session_sort = (Sort) session.getAttribute("sortParams");
//            if (session_sort != null) {
//                page = productService.findPaginatedWithSort(pageNo - 1,
//                        pageSize,
//                        session_sort);
//            } else {
//                //logger.info("sort params are not present in session");
//                page = productService.findPaginated(pageNo - 1,
//                        pageSize);
//
//            }
//        }
//        model.addAttribute("accounts", page.getContent());
//        model.addAttribute("current_page", page.getNumber() + 1);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("pageSize",pageSize);
        return "accounts_for_sale";

    }

    @RequestMapping(value = "/instagram_info", method = RequestMethod.GET)
    public ModelAndView getInstaInfoPage(ModelAndView model) {
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
        String parameters = "business_discovery.username(bluebottle){followers_count,media_count,biography}";
        FacebookClient client = new DefaultFacebookClient("EAAIixFooZAqQBABRyyifK9I1kQfBuE83eX91KbZARZBLRHWqrx4ZBiUdXOMYNYjiv5KnOBLWXnc1JOO9ZC6Oo4vR6jbMK1vW5QKfgzZCoh7ZAYRZCnFnMfmZBWYYIoCkZApZBoTud3sOIU3sm7ls4H6XRZBhdpK2OM3HRbsVfHhUhbaQaXtgIG4BG0QgAPPsjHp4wRtZCYCq2rz4XgmgyghzLVjtLmWNI0kUMCLQZD", Version.VERSION_7_0);
        IgUser igProfile = client.fetchObject("17841403153343619", IgUser.class,
                Parameter.with("fields", parameters));
        IgUser profileInfo = igProfile.getBusinessDiscovery();
        model.setViewName("instagram_info");
        model.addObject("accountInfo", profileInfo.getBiography());
        return model;
    }
}
