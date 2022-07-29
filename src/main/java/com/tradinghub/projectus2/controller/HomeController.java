package com.tradinghub.projectus2.controller;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.service.AccountService;
import com.tradinghub.projectus2.utils.ContentHelper;
import com.tradinghub.projectus2.utils.HomeSortHelper;
import com.tradinghub.projectus2.utils.SortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    AccountService accountService;
    @Autowired
    SortHelper helper;
    @Autowired
    ContentHelper list_helper;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(
            @RequestParam(defaultValue = "1", name = "pageNo") Integer pageNo,
            @RequestParam(defaultValue = "5", name = "pageSize") Integer pageSize,
            @RequestParam(required = false) Boolean drop,
            @RequestParam(required = false, name = "category") String category,
            @RequestParam(required = false, name = "followers") String followers,
            @RequestParam(required = false, name = "price") String price,
            Model model,
            HttpSession session) {

        if (drop != null) {
            if (drop) {
                session.removeAttribute("sortParams");
            }
        }
        String url="home";
        Page<Account> page;
        Optional<Sort> sortParams= helper.getSort(price,followers);
        if (category != null) {
            if(category.equals("'inst'")) {
                url = "redirect:/inst" ;
            }
            return url;
        }
        if (sortParams.isPresent()) {
            page = accountService
                    .findPaginatedWithSort(pageNo - 1, pageSize, sortParams.get());
            session.setAttribute("sortParams", sortParams.get());
        } else {
            Sort session_sort = (Sort) session.getAttribute("sortParams");
            if (session_sort != null) {
                page = accountService.findPaginatedWithSort(pageNo - 1,
                        pageSize,
                        session_sort);
            } else {
                page = accountService.findPaginated(pageNo - 1, pageSize);
            }
        }
        url="/home/home.html";
        List<Account[]> list =list_helper.getList(page.getContent(),4);
        model.addAttribute("items",list);
        model.addAttribute("current_page",page.getNumber()+1);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize",pageSize);
        return url;
    }

}
