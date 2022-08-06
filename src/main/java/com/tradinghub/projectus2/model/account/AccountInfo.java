package com.tradinghub.projectus2.model.account;

import com.tradinghub.projectus2.model.enums.Currency;
import com.tradinghub.projectus2.model.enums.MediaType;
import com.tradinghub.projectus2.model.user.UserInfo;

import javax.persistence.*;

@Entity
@Table(name="account_info")
public class AccountInfo {
    public AccountInfo() {
    }

    public AccountInfo(Account account) {
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "header")
    private String header;
    @Column(nullable = false,name = "text")
    private String text;
    @Column(nullable = false,name = "income")
    private Integer income;
    @Column(nullable = false,name = "followers")
    private Integer followers;
    @Column(nullable = false,unique=true,name = "url")
    private String url;
    @Column(nullable = false,name = "activationCode")
    private String activationCode;
    @Column(nullable = false,name = "price")
    private Integer price;
    @Column(name="expenses",nullable=true)
    private Integer expenses;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private MediaType type;
    @OneToOne(mappedBy = "accountInfo")
    private Account account;

    public Integer getExpenses() {
        return expenses;
    }

    public void setExpenses(Integer expenses) {
        this.expenses = expenses;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
