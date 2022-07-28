package com.tradinghub.projectus2.model.instagram;

import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.model.intefaces.Account;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class InstagramAccount extends Account {
    public InstagramAccount() {
        this.accountInfo=new InstagramAccountInfo(this);
    }

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "accountInfo_id")
    private InstagramAccountInfo accountInfo;


    public InstagramAccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(InstagramAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}
