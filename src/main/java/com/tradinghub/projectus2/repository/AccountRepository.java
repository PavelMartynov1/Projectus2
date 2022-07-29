package com.tradinghub.projectus2.repository;

import com.tradinghub.projectus2.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}
