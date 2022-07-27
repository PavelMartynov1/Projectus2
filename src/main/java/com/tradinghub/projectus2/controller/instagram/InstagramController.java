package com.tradinghub.projectus2.controller.instagram;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.instagram.IgUser;
import com.tradinghub.projectus2.controller.user.UserController;
import com.tradinghub.projectus2.errorExeptions.WrongCodeException;
import com.tradinghub.projectus2.model.Product;
import com.tradinghub.projectus2.service.ProductService;
import com.tradinghub.projectus2.utils.SortHelper;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value="/inst")
public class InstagramController {
    @Autowired
    SortHelper sortHelper;
    @Autowired
    ProductService productService;
    Logger logger = LoggerFactory.getLogger(InstagramController.class);


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
        Page<Product> page;
        Optional<Sort> sortParams = sortHelper.getSort(price, followers);
        if (sortParams.isPresent()) {
            //logger.info("sort params are present");
            page = productService.findPaginatedWithSort(pageNo - 1,
                    pageSize,
                    sortParams.get());
            session.setAttribute("sortParams", sortParams.get());
        } else {
            //logger.info("sort params are not present and are taken from session");
            Sort session_sort = (Sort) session.getAttribute("sortParams");
            if (session_sort != null) {
                page = productService.findPaginatedWithSort(pageNo - 1,
                        pageSize,
                        session_sort);
            } else {
                //logger.info("sort params are not present in session");
                page = productService.findPaginated(pageNo - 1,
                        pageSize);

            }
        }
        model.addAttribute("accounts", page.getContent());
        model.addAttribute("current_page", page.getNumber() + 1);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize",pageSize);
        return "accounts_for_sale";

    }

    @RequestMapping(value = "/add_product", method = RequestMethod.GET)
    public String getAddProductPage(Model model, HttpSession session) {
        Product product = new Product();
        String code = RandomString.make(8);
        session.setAttribute("account_code", code);
        product.setCode(code);
        model.addAttribute("product", product);
        return "add_product";
    }

    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    public String addProductPage(@ModelAttribute("product")
                                 @Valid Product product, HttpSession session, Model model) {
        String code = null;
        if (session != null) {
            code = (String) session.getAttribute("account_code");
        }
        logger.info("Code: " + code);
        try {
            product = productService.verifyAccount(product, code);
        } catch (WrongCodeException e) {
            model.addAttribute("codeException", e);
            return "add_product";
        }
        productService.saveProduct(product);
        return "add_product_success";
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
