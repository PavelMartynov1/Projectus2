package com.tradinghub.projectus2.utils.pageHelper;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.service.AccountService;
import com.tradinghub.projectus2.service.UserService;
import com.tradinghub.projectus2.utils.sort.profileSort.ProfileSortHelper;
import com.tradinghub.projectus2.utils.sort.profileSort.ProfileSortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;
@Service
public class ProfilePageHelper {
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(ProfilePageHelper.class);
    Page<Account> page;

    public Page<Account> getPage(ProfileSortParams sortParams, HttpSession session) {
        ProfileSortHelper helper = new ProfileSortHelper();
        Optional<Sort> lookUpsSort = helper.getSort(sortParams.getLookUpsOrder());
        if (sortParams.getAccountStatus() != null || sortParams.getLookUpsOrder() != null) {
            logger.info("sort params are present");
            session.setAttribute("profileSort", sortParams);
            return page(sortParams, lookUpsSort);
        } else{
            logger.info("sort params are not present");
            ProfileSortParams sessionParams =(ProfileSortParams) session.getAttribute("profileSort");
            if(sessionParams==null){
                logger.info("session params are null");
                return page(sortParams,lookUpsSort);
            } else{
                logger.info("session params are not null");
                sessionParams.setPageNo(sortParams.getPageNo());
                sessionParams.setPageSize(sortParams.getPageSize());
                return page(sessionParams, lookUpsSort);
            }
        }

    }

    private Page<Account> page(ProfileSortParams sortParams, Optional<Sort> lookUpsSort) {
        if (sortParams.getLookUpsOrder() == null & sortParams.getAccountStatus() == null) {
            logger.info("status and watches are null");
            page = accountService.findAccountsByUserId(sortParams.getUserId(),sortParams,lookUpsSort);
            return page;
        }
        if(sortParams.getLookUpsOrder() != null & sortParams.getAccountStatus() != null) {
            logger.info("status and watches are not null");
            page = accountService.getUserAccountsByStatusAndLookUpsOrder(sortParams, lookUpsSort);
            return page;
        }
        if(sortParams.getAccountStatus()==null){
            logger.info(" status is null");
            return page=accountService.getUserAccountsByLookUpsOrder(sortParams, lookUpsSort);
        }
        logger.info("else");
        return accountService.getUserAccountsByStatusAndLookUpsOrder(sortParams, lookUpsSort);
    }
}

