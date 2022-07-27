package com.tradinghub.projectus2.controller;

import com.tradinghub.projectus2.model.Product;
import com.tradinghub.projectus2.service.ProductService;
import com.tradinghub.projectus2.utils.ContentHelper;
import com.tradinghub.projectus2.utils.HomeSortHelper;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    ProductService productService;
    @Autowired
    ContentHelper helper;

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
        String url;
        Page<Product> page;
        Optional<Sort> sortParams=HomeSortHelper.newBuilder()
                .setFollowers(followers)
                .setPrice(price).build().getSortParams();
        if (category != null) {
            url = "redirect:/" + category;
            return url;
        }
        if (sortParams.isPresent()) {
            page = productService
                    .findPaginatedWithSort(pageNo - 1, pageSize, sortParams.get());
            session.setAttribute("sortParams", sortParams.get());
        } else {
            Sort session_sort = (Sort) session.getAttribute("sortParams");
            if (session_sort != null) {
                page = productService.findPaginatedWithSort(pageNo - 1,
                        pageSize,
                        session_sort);
            } else {
                page = productService.findPaginated(pageNo - 1, pageSize);
            }
        }
        url="/home/Home.html";
        List<Product[]> list =helper.getList(page.getContent(),4);
        model.addAttribute("items",list);
        model.addAttribute("current_page",page.getNumber()+1);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("pageSize",pageSize);
        return url;
    }

}
