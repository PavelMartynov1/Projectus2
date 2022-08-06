package com.tradinghub.projectus2.utils;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.service.AccountService;
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


    public Page<Account> getPage(SortParams sortParams, HttpSession session) {
        SortHelper helper = new SortHelper();
        Optional<Sort> sortOrder = helper.getSort(sortParams.getPriceOrder(), sortParams.getFollowersOrder());
        if (sortParams.getCategory() != null || sortParams.getMediaType() != null) {
            session.setAttribute("sortParams", sortParams);
            return  page(sortParams, sortOrder);
        } else {
            SortParams sessionParams = (SortParams) session.getAttribute("sortParams");
            if (sessionParams == null) {
               return  page(sortParams, sortOrder);
            } else {
                sessionParams.setPageNo(sortParams.getPageNo());
                sessionParams.setPageSize(sortParams.getPageSize());
                return page(sessionParams, sortOrder);
            }
        }
    }

    private Page<Account> page(SortParams sortParams, Optional<Sort> sortOrder) {
        logger.warn(sortParams.toString());
        Page<Account> page;
        if (sortParams.getCategory() == null & sortParams.getMediaType() == null) {
            logger.info("category and type null");
            page = accountService.findAllAccountsByPriceAndFollowersWithOrder(sortParams, sortOrder);
            return page;
        }
        if (sortParams.getCategory() != null & sortParams.getMediaType() != null) {
            logger.info("category and type are not null");
            page = accountService.findPaginatedByCategoryAndMediaTypeAndPriceAndFollowersWithOrder(sortParams, sortOrder);
            return page;
        }
        if (sortParams.getCategory() == null) {
            logger.info("category  null");
            page = accountService.findPaginatedByMediaTypeAndPriceAndFollowersWithOrder(sortParams, sortOrder);
            return page;
        }
        if (sortParams.getMediaType() == null) {
            logger.info("type null");
            page = accountService.findPaginatedByCategoryAndPriceAndFollowersWithOrder(sortParams, sortOrder);
            return page;
        }
        return null;
    }
}

