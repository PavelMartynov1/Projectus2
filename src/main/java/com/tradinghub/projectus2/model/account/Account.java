package com.tradinghub.projectus2.model.account;

import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.model.user.UserInfo;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    public Account() {
        this.accountInfo = new AccountInfo(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    private AccountCategory category;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "accountInfo_id")
    private AccountInfo accountInfo;
    @Column(name="withEmail",nullable=true)
    private Boolean withEmail;
    @Column(name="pin",nullable=true)
    private Boolean pin;
    @Column(name="colored",nullable=true)
    private Boolean colored;
    @ManyToOne
    @JoinColumn(name="userInfo_id")
    private UserInfo userInfo;

    public Boolean getWithEmail() {
        return withEmail;
    }

    public void setWithEmail(Boolean withEmail) {
        this.withEmail = withEmail;
    }

    public Boolean getPin() {
        return pin;
    }

    public void setPin(Boolean pin) {
        this.pin = pin;
    }

    public Boolean getColored() {
        return colored;
    }

    public void setColored(Boolean colored) {
        this.colored = colored;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

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
