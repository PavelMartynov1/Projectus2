package com.tradinghub.projectus2.controller;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.utils.ContentHelper;
import com.tradinghub.projectus2.utils.pageHelper.PageHelper;
import com.tradinghub.projectus2.utils.sort.homeSort.HomeSortParams;
import com.tradinghub.projectus2.utils.sort.homeSort.HomeSortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.List;



@Controller
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    HomeSortHelper helper;
    @Autowired
    ContentHelper contentHelper;
    @Autowired
    PageHelper pageHelper;
    @RequestMapping(value = "/home-test", method = RequestMethod.GET)
    public String getHomeTestPage(@RequestParam(defaultValue = "1", name = "pageNo") Integer pageNo,
                                  @RequestParam(defaultValue = "3", name = "pageSize") Integer pageSize,
                                  @RequestParam(required = false) Boolean drop,
                                  @RequestParam(required = false, name = "category") String category,
                                  @RequestParam(required = false, name = "mediaType") String mediaType,
                                  @RequestParam(required = false, name = "followersOrder") String followersOrder,
                                  @RequestParam(required = false, name = "priceOrder") String priceOrder,
                                  @RequestParam(required = false, name = "priceFrom") Integer priceFrom,
                                  @RequestParam(required = false, name = "priceUp") Integer priceUp,
                                  @RequestParam(required = false, name = "followersFrom") Integer followersFrom,
                                  @RequestParam(required = false, name = "followersUp") Integer followersUp,
                                  Model model,
                                  HttpSession session) {
        if (drop != null) {
            if (drop) {
                session.removeAttribute("homeSortParams");
            }
        }
        HomeSortParams homeSortParams = HomeSortParams.newBuilder().setPageNo(pageNo)
                .setPageSize(pageSize)
                .setCategory(category)
                .setFollowersOrder(followersOrder)
                .setPriceOrder(priceOrder)
                .setPriceFrom(priceFrom)
                .setPriceUp(priceUp)
                .setMediaType(mediaType)
                .setFollowersFrom(followersFrom)
                .setFollowersUp(followersUp).build();
        Page<Account> page=pageHelper.getPage(homeSortParams,session);
        if(page==null){
            logger.info("page is null");
            return "redirect:/";
        }
        List<Account[]> list=contentHelper.getList(page.getContent(),3);
        model.addAttribute("accounts",list);
        model.addAttribute("current_page",page.getNumber()+1);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("pageSize",pageSize);
        return "home/home.html";
    }
}
