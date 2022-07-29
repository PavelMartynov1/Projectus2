package com.tradinghub.projectus2.model.account;

import com.tradinghub.projectus2.model.enums.AccountCategory;
import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
    public Account() {
        this.accountInfo=new AccountInfo(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique=true,name = "email")
    private String email;
    @Column(nullable = false,name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    private AccountCategory category;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "accountInfo_id")
    private AccountInfo accountInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountCategory getCategory() {
        return category;
    }

    public void setCategory(AccountCategory category) {
        this.category = category;
    }
}
