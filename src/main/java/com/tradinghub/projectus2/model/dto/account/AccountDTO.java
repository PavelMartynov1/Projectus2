package com.tradinghub.projectus2.model.dto.account;

import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.model.enums.Currency;
import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.MediaType;
import com.tradinghub.projectus2.model.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

public class AccountDTO {
    @NotBlank(message = "email error")
    @Email
    private String email;
    @NotBlank(message = "password error")
    private String password;
    @NotBlank(message = "category error")
    private String category;
    @NotBlank(message = "Please,choose your category")
    private String mediaType;
    private Integer expenses;
    @NotBlank(message = "header error")
    private String header;
    @NotBlank(message = "text error")
    private String text;
    @NotBlank(message="income error")
    private Integer income;
    //@NotBlank(message = "followers error")
    private Integer followers;
    @NotBlank(message = "url error")
    private String url;
    @NotBlank(message = "activationCode error")
    private String activationCode;
    @NotBlank(message = "price error")
    private Integer price;
    //@NotBlank(message = "currency error")
    @Null
    private String currency;
    public Account build(User user){
        Account account=new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setCategory(AccountCategory.valueOf(category));
        account.getAccountInfo().setType(MediaType.valueOf(mediaType));
        account.getAccountInfo().setHeader(header);
        account.getAccountInfo().setText(text);
        account.getAccountInfo().setIncome(income);
        account.getAccountInfo().setFollowers(111);
        account.getAccountInfo().setUrl(url);
        account.getAccountInfo().setExpenses(expenses);
        account.setUserInfo(user.getUserInfo());
        account.getAccountInfo().setActivationCode(activationCode);
        account.getAccountInfo().setPrice(price);
        account.getAccountInfo().setCurrency(Currency.grn);
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
