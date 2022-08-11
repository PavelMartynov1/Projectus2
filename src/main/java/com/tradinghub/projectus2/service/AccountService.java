package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.account.AccountInfo;
import com.tradinghub.projectus2.model.enums.AccountStatus;
import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.repository.AccountRepository;
import com.tradinghub.projectus2.utils.sort.SortParams;
import com.tradinghub.projectus2.utils.sort.homeSort.HomeSortParams;
import com.tradinghub.projectus2.utils.sort.profileSort.ProfileSortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private String fcbToken="EAAIixFooZAqQBALKz6su5AkEFO8AvwfxaccRb8liY6VpwKg0GwG7oYGgabgj8xOiXWSe3ZAhV179nlZB4TqVQYFNHD6oNNZCIRqH4wp2pM3ChxRcJDK4YxJeRo9pZAMn8E3c0k6RXYDOreww2siJFtDEUoZBQKGCHR63l0hbg9zwE00IK8VyjvYSubiRBtkL9FgE1y5nOo0StPgUodBcFT9DtFBsZA6KakZD";
    private String instId="17841403153343619";
    Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService;

    public AccountService() {
    }
    private Pageable getPage(SortParams homeSortParams, Optional<Sort> sortOrder) {
        logger.warn(homeSortParams.toString());
        Pageable paging;
        if(sortOrder.isPresent()) {
            paging = PageRequest.of(homeSortParams.getPageNo() - 1, homeSortParams.getPageSize(), sortOrder.get());
        } else{
            paging = PageRequest.of(homeSortParams.getPageNo() - 1, homeSortParams.getPageSize());
        }
        return paging;
    }
    private boolean isOwner(Account account,String username){
        User user=account.getUser();
        return user.getUsername().equals(username);

    }
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public Optional<Account> findById(Long id, Principal principal){
        Optional<Account> account=accountRepository.findById(id);
        if(account.isPresent()) {
            if(principal==null)
            {
                increaseLookups(account.get());
                return account;
            }
            if (!isOwner(account.get(), principal.getName())) {
                increaseLookups(account.get());
            }
        }
        return account;
    }
    private void increaseLookups(Account account){
        AccountInfo accountInfo=account.getAccountInfo();
        accountInfo.setLookUps(accountInfo.getLookUps()+1);
        accountRepository.increaseLookUps(accountInfo,account.getId());
    }
    public Page<Account> getUserAccountsByStatusAndLookUpsOrder(ProfileSortParams sortParams, Optional<Sort> sortOrder){
        Pageable paging=getPage(sortParams,sortOrder);
        return accountRepository.findAccountsByIdAndStatusAndLookUpsOrder(sortParams.getUserId(),AccountStatus.valueOf(sortParams.getAccountStatus()),
                paging);

    }
    public Page<Account> getUserAccountsByLookUpsOrder(ProfileSortParams sortParams, Optional<Sort> sortOrder){
        Pageable paging=getPage(sortParams,sortOrder);
        return accountRepository.findAccountsByUserId(sortParams.getUserId(),paging);

    }

    public Page<Account> findPaginatedByCategoryAndMediaTypeAndPriceAndFollowersWithOrder(HomeSortParams homeSortParams, Optional<Sort> sortOrder){

        Pageable paging = getPage(homeSortParams, sortOrder);getPage(homeSortParams, sortOrder);
        return accountRepository.findAccountByCategoryAndMediaTypeAndPriceAndFollowersWithSort(homeSortParams.getCategory(),
                homeSortParams.getMediaType(), homeSortParams.getPriceFrom(), homeSortParams.getPriceUp(), homeSortParams.getFollowersFrom(), homeSortParams.getFollowersUp(),paging);
    }


    public Page<Account> findPaginatedByCategoryAndPriceAndFollowersWithOrder(HomeSortParams homeSortParams, Optional<Sort> sortOrder){
        Pageable paging = getPage(homeSortParams, sortOrder);
        return accountRepository
                .findAccountByCategoryAndPriceAndFollowersWithSort(homeSortParams.getCategory(),
                homeSortParams.getPriceFrom(), homeSortParams.getPriceUp(), homeSortParams.getFollowersFrom(), homeSortParams.getFollowersUp(),paging);
    }



    public Page<Account> findPaginatedByMediaTypeAndPriceAndFollowersWithOrder(HomeSortParams homeSortParams, Optional<Sort> sortOrder){
        Pageable paging = getPage(homeSortParams, sortOrder);
        return accountRepository
                .findAccountByMediaTypeAndPriceAndFollowersWithSort(homeSortParams.getMediaType(),
                        homeSortParams.getPriceFrom(), homeSortParams.getPriceUp(), homeSortParams.getFollowersFrom(), homeSortParams.getFollowersUp(),paging);
    }

    public Page<Account> findAllAccountsByPriceAndFollowersWithOrder(HomeSortParams homeSortParams, Optional<Sort> sortOrder){
        Pageable paging = getPage(homeSortParams, sortOrder);
        logger.info("meidaType "+(homeSortParams.getMediaType()));
        return accountRepository
                .findAllAccountsByPriceAndFollowersWithSort(homeSortParams.getPriceFrom(), homeSortParams.getPriceUp(),
                        homeSortParams.getFollowersFrom(), homeSortParams.getFollowersUp(),paging);
    }

    public Page<Account> findAccountsByUserId(Long id, SortParams sortParams,Optional <Sort> sortOrder){
        Pageable paging = getPage(sortParams, sortOrder);
        return accountRepository.findAccountsByUserId(id,paging);
    }



    public Account verifyAccount(Account account,String code){
//        String parameters="business_discovery.username("
//                +account.getUsername()
//                +"){followers_count,media_count,biography}";
//        logger.info(parameters);
//        FacebookClient client=new DefaultFacebookClient(fcbToken, Version.LATEST);
//        IgUser igProfile =client.fetchObject(instId,
//                IgUser.class,
//                Parameter.with("fields",parameters));
//        IgUser profileInfo=igProfile.getBusinessDiscovery();
//        String biography=profileInfo.getBiography();
//        if(!biography.equals(code)){
//            throw new WrongCodeException("Code is wrong");
//        }
//        account.setFollowers(profileInfo.getFollowersCount());
        return account;
    }
}
