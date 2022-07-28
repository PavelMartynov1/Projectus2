package com.tradinghub.projectus2.service;
import com.tradinghub.projectus2.model.intefaces.Account;
import com.tradinghub.projectus2.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    private String fcbToken="EAAIixFooZAqQBALKz6su5AkEFO8AvwfxaccRb8liY6VpwKg0GwG7oYGgabgj8xOiXWSe3ZAhV179nlZB4TqVQYFNHD6oNNZCIRqH4wp2pM3ChxRcJDK4YxJeRo9pZAMn8E3c0k6RXYDOreww2siJFtDEUoZBQKGCHR63l0hbg9zwE00IK8VyjvYSubiRBtkL9FgE1y5nOo0StPgUodBcFT9DtFBsZA6KakZD";

    private String instId="17841403153343619";
    Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    AccountRepository accountRepository;
//    public List<Product> findAll(){
//        return productRepository.findAll();
//    }
    public void saveAccount(Account account){
        accountRepository.save(account);

    }
//    public Page <Product> findPaginated(int pageNo, int pageSize){
//        Pageable paging= PageRequest.of(pageNo,pageSize);
//        Page<Product> pagedResult=productRepository.findAll(paging);
//        return pagedResult;
//    }
//    public Page<Product> findPaginatedWithSort(int pageNo, int pageSize, Sort sort){
//        Pageable paging= PageRequest.of(pageNo,pageSize,sort);
//        Page<Product> pagedResult=productRepository.findAll(paging);
//        return pagedResult;
//    }
//    public Product verifyAccount(Product account,String code){
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
//        return account;
//    }
//    public boolean saveProduct(Product product){
//       //implement checking process and add exceptions
//        productRepository.save(product);
//        return true;
//    }
}
