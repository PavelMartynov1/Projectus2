package com.tradinghub.projectus2.utils.pageHelper;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.service.AccountService;
import com.tradinghub.projectus2.utils.sort.homeSort.HomeSortParams;
import com.tradinghub.projectus2.utils.sort.homeSort.HomeSortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class PageHelper {
    @Autowired
    AccountService accountService;
    Logger logger = LoggerFactory.getLogger(PageHelper.class);


    public Page<Account> getPage(HomeSortParams homeSortParams, HttpSession session) {
        HomeSortHelper helper = new HomeSortHelper();
        Optional<Sort> sortOrder = helper.getSort(homeSortParams.getPriceOrder(), homeSortParams.getFollowersOrder());
        if (homeSortParams.getCategory() != null || homeSortParams.getMediaType() != null) {
            session.setAttribute("sortParams", homeSortParams);
            return  page(homeSortParams, sortOrder);
        } else {
            HomeSortParams sessionParams = (HomeSortParams) session.getAttribute("sortParams");
            if (sessionParams == null) {
               return  page(homeSortParams, sortOrder);
            } else {
                sessionParams.setPageNo(homeSortParams.getPageNo());
                sessionParams.setPageSize(homeSortParams.getPageSize());
                return page(sessionParams, sortOrder);
            }
        }
    }

    private Page<Account> page(HomeSortParams homeSortParams, Optional<Sort> sortOrder) {
        logger.warn(homeSortParams.toString());
        Page<Account> page;
        if (homeSortParams.getCategory() == null & homeSortParams.getMediaType() == null) {
            logger.info("category and type null");
            page = accountService.findAllAccountsByPriceAndFollowersWithOrder(homeSortParams, sortOrder);
            return page;
        }
        if (homeSortParams.getCategory() != null & homeSortParams.getMediaType() != null) {
            logger.info("category and type are not null");
            page = accountService.findPaginatedByCategoryAndMediaTypeAndPriceAndFollowersWithOrder(homeSortParams, sortOrder);
            return page;
        }
        if (homeSortParams.getCategory() == null) {
            logger.info("category  null");
            page = accountService.findPaginatedByMediaTypeAndPriceAndFollowersWithOrder(homeSortParams, sortOrder);
            return page;
        }
        if (homeSortParams.getMediaType() == null) {
            logger.info("type null");
            page = accountService.findPaginatedByCategoryAndPriceAndFollowersWithOrder(homeSortParams, sortOrder);
            return page;
        }
        return null;
    }
}

