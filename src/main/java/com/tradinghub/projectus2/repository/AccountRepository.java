package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.AccountCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a  from Account a where a.category= :category")
    Page<Account> findAccountByCategoryWithSort(@Param("category") AccountCategory category,Pageable page);

    @Query("select a  from Account a where a.category = :category" )
    Page<Account> findAccountByCategory(@Param("category") AccountCategory category, Pageable page);
}
