package com.tradinghub.projectus2.model.dto.instagramdto;

import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.model.enums.Currency;
import com.tradinghub.projectus2.model.instagram.InstagramAccount;
import com.tradinghub.projectus2.model.intefaces.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class InstagramDTO {
    @NotBlank(message = "email error")
    @Email
    private String email;
    @NotBlank(message = "password error")
    private String password;
    @NotBlank(message = "category error")
    private String category;
    @NotBlank(message = "header error")
    private String header;
    @NotBlank(message = "text error")
    private String text;
    private String income;
    //@NotBlank(message = "followers error")
    private String followers;
    @NotBlank(message = "url error")
    private String url;
    @NotBlank(message = "activationCode error")
    private String activationCode;
    @NotBlank(message = "price error")
    private String price;
    @NotBlank(message = "currency error")
    private String currency;
    public InstagramAccount build(){
        InstagramAccount account=new InstagramAccount();
        account.setEmail(email);
        account.setPassword(password);
        account.setCategory(AccountCategory.valueOf(category));
        account.getAccountInfo().setHeader(header);
        account.getAccountInfo().setText(text);
        account.getAccountInfo().setIncome(income);
        account.getAccountInfo().setFollowers(111);
        account.getAccountInfo().setUrl(url);
        account.getAccountInfo().setActivationCode(activationCode);
        account.getAccountInfo().setPrice(price);
        account.getAccountInfo().setCurrency(Currency.valueOf(currency));
        return account;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
