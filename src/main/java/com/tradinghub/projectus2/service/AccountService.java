package com.tradinghub.projectus2.service;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.repository.AccountRepository;
import com.tradinghub.projectus2.utils.SortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private String fcbToken="EAAIixFooZAqQBALKz6su5AkEFO8AvwfxaccRb8liY6VpwKg0GwG7oYGgabgj8xOiXWSe3ZAhV179nlZB4TqVQYFNHD6oNNZCIRqH4wp2pM3ChxRcJDK4YxJeRo9pZAMn8E3c0k6RXYDOreww2siJFtDEUoZBQKGCHR63l0hbg9zwE00IK8VyjvYSubiRBtkL9FgE1y5nOo0StPgUodBcFT9DtFBsZA6KakZD";
    private String instId="17841403153343619";
    Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    AccountRepository accountRepository;

    public AccountService() {
    }
    private Pageable getPage(SortParams sortParams, Optional<Sort> sortOrder) {
        logger.warn(sortParams.toString());
        Pageable paging;
        if(sortOrder.isPresent()) {

            paging = PageRequest.of(sortParams.getPageNo() - 1, sortParams.getPageSize(), sortOrder.get());
        } else{
            paging = PageRequest.of(sortParams.getPageNo() - 1, sortParams.getPageSize());
        }
        return paging;
    }
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    public void saveAccount(Account account){
        accountRepository.save(account);
    }
    public Optional<Account> findById(String id){
        return accountRepository.findById(Long.valueOf(id));

    }

    public Page<Account> findPaginatedByCategoryAndMediaTypeAndPriceAndFollowersWithOrder(SortParams sortParams,Optional<Sort> sortOrder){

        Pageable paging = getPage(sortParams, sortOrder);getPage(sortParams, sortOrder);
        return accountRepository.findAccountByCategoryAndMediaTypeAndPriceAndFollowersWithSort(sortParams.getCategory(),
                sortParams.getMediaType(),sortParams.getPriceFrom(),sortParams.getPriceUp(),sortParams.getFollowersFrom(),sortParams.getFollowersUp(),paging);
    }


    public Page<Account> findPaginatedByCategoryAndPriceAndFollowersWithOrder(SortParams sortParams,Optional<Sort> sortOrder){
        Pageable paging = getPage(sortParams, sortOrder);
        return accountRepository
                .findAccountByCategoryAndPriceAndFollowersWithSort(sortParams.getCategory(),
                sortParams.getPriceFrom(),sortParams.getPriceUp(),sortParams.getFollowersFrom(),sortParams.getFollowersUp(),paging);
    }



    public Page<Account> findPaginatedByMediaTypeAndPriceAndFollowersWithOrder(SortParams sortParams,Optional<Sort> sortOrder){
        Pageable paging = getPage(sortParams, sortOrder);
        return accountRepository
                .findAccountByMediaTypeAndPriceAndFollowersWithSort(sortParams.getMediaType(),
                        sortParams.getPriceFrom(),sortParams.getPriceUp(),sortParams.getFollowersFrom(),sortParams.getFollowersUp(),paging);
    }

    public Page<Account> findAllAccountsByPriceAndFollowersWithOrder(SortParams sortParams,Optional<Sort> sortOrder){
        Pageable paging = getPage(sortParams, sortOrder);
        logger.info("meidaType "+(sortParams.getMediaType()));
        return accountRepository
                .findAllAccountsByPriceAndFollowersWithSort(sortParams.getPriceFrom(),sortParams.getPriceUp(),
                        sortParams.getFollowersFrom(),sortParams.getFollowersUp(),paging);
    }














    public Page<Account> findPaginatedByCategory(AccountCategory category, int pageNo, int pageSize){
        Pageable paging= PageRequest.of(pageNo,pageSize);
        Page<Account> pagedResult=accountRepository.findAccountByCategory(category,paging);
        return pagedResult;
    }
    public Page<Account> findPaginatedByCategoryWithSort(AccountCategory category,int pageNo, int pageSize,Sort sort){
        Pageable paging= PageRequest.of(pageNo,pageSize,sort);
        Page<Account> pagedResult=accountRepository.findAccountByCategoryWithSort(category,paging);
        return pagedResult;
    }

    public Page<Account> findPaginated(int pageNo, int pageSize){
        Pageable paging= PageRequest.of(pageNo,pageSize);
        Page<Account> pagedResult=accountRepository.findAll(paging);
        return pagedResult;
    }
    public Page<Account> findPaginatedWithSort(int pageNo, int pageSize, Sort sort){
        Pageable paging= PageRequest.of(pageNo,pageSize,sort);
        Page<Account> pagedResult=accountRepository.findAll(paging);
        return pagedResult;
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
