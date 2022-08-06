package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.model.enums.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a  from Account a where a.category= :category")
    Page<Account> findAccountByCategoryWithSort(@Param("category") AccountCategory category, Pageable page);

    @Query("select a  from Account a where a.category = :category")
    Page<Account> findAccountByCategory(@Param("category") AccountCategory category, Pageable page);

/*
searches accounts by category and mediaType
 */

    @Query("select a from Account a  where a.category = :category" +
            " and a.accountInfo.type =:type and a.accountInfo.price > :priceFrom" +
            " and a.accountInfo.price < :priceUp and a.accountInfo.followers > :followersFrom and " +
            "a.accountInfo.followers < :followersUp")
    Page<Account> findAccountByCategoryAndMediaTypeAndPriceAndFollowersWithSort(@Param("category") AccountCategory category,
                                                                                @Param("type") MediaType type,
                                                                                @Param("priceFrom") Integer priceFrom,
                                                                                @Param("priceUp") Integer priceUp,
                                                                                @Param("followersFrom") Integer followersFrom,
                                                                                @Param("followersUp") Integer followersUp, Pageable page);

    /*
searches accounts only  by category
 */
    @Query("select a from Account a  where a.category = :category" +
            " and a.accountInfo.price > :priceFrom" +
            " and a.accountInfo.price < :priceUp and a.accountInfo.followers > :followersFrom and " +
            "a.accountInfo.followers < :followersUp")
    Page<Account> findAccountByCategoryAndPriceAndFollowersWithSort(@Param("category") AccountCategory category,
                                                                                @Param("priceFrom") Integer priceFrom,
                                                                                @Param("priceUp") Integer priceUp,
                                                                                @Param("followersFrom") Integer followersFrom,
                                                                                @Param("followersUp") Integer followersUp, Pageable page);
    /*
searches accounts only  by mediaType
*/
    @Query("select a from Account a  where" +
            " a.accountInfo.type =:type and a.accountInfo.price > :priceFrom" +
            " and a.accountInfo.price < :priceUp and a.accountInfo.followers > :followersFrom and " +
            "a.accountInfo.followers < :followersUp")
    Page<Account> findAccountByMediaTypeAndPriceAndFollowersWithSort(
                                                                                @Param("type") MediaType type,
                                                                                @Param("priceFrom") Integer priceFrom,
                                                                                @Param("priceUp") Integer priceUp,
                                                                                @Param("followersFrom") Integer followersFrom,
                                                                                @Param("followersUp") Integer followersUp, Pageable page);
    /*
searches all accounts
*/
    @Query("select a from Account a where  a.accountInfo.price > :priceFrom" +
            " and a.accountInfo.price < :priceUp and a.accountInfo.followers > :followersFrom and " +
            "a.accountInfo.followers < :followersUp")
    Page<Account> findAllAccountsByPriceAndFollowersWithSort(@Param("priceFrom") Integer priceFrom,
                                                             @Param("priceUp") Integer priceUp,
                                                             @Param("followersFrom") Integer followersFrom,
                                                             @Param("followersUp") Integer followersUp, Pageable page);


}
