package com.tradinghub.projectus2.model.instagram;

import com.tradinghub.projectus2.model.enums.Currency;
import com.tradinghub.projectus2.model.intefaces.Account;
import com.tradinghub.projectus2.model.intefaces.AccountInfo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "instInfo")
public class InstagramAccountInfo implements AccountInfo {
    public InstagramAccountInfo(@NotNull InstagramAccount account) {
        this.account = account;
    }

    public InstagramAccountInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name = "header")
    private String header;
    @Column(nullable = false,name = "text")
    private String text;
    @Column(nullable = false,name = "income")
    private String income;
    @Column(nullable = false,name = "followers")
    private Integer followers;
    @Column(nullable = false,unique=true,name = "url")
    private String url;
    @Column(nullable = false,name = "activationCode")
    private String activationCode;
    @Column(nullable = false,name = "price")
    private String price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToOne(mappedBy = "accountInfo")
    private InstagramAccount account;

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public void setCurrency(String currency) {
        this.currency = Currency.valueOf(currency);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstagramAccount getAccount() {
        return account;
    }

    public void setAccount(InstagramAccount account) {
        this.account = account;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}
