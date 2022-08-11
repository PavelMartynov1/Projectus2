package com.tradinghub.projectus2.model.dto.account;

import com.tradinghub.projectus2.model.account.Account;
import com.tradinghub.projectus2.model.enums.AccountCategory;
import com.tradinghub.projectus2.model.enums.AccountStatus;
import com.tradinghub.projectus2.model.enums.MediaType;
import com.tradinghub.projectus2.model.user.User;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class InstagramDTO {
    private String code;

    @NotBlank(message="url should be entered")
    private String url;
    @NotNull(message= "price may not be empty")
    @Range(min = 1)
    private Integer price;
    @NotBlank(message="Text should be entered")
    private String text;
    @NotBlank(message="MediaType should be entered")
    private String mediaType;
    @NotBlank(message="Text should be entered")
    private String header;
    private Integer income;
    private Integer expenses;
    @NotBlank(message = "Give answer")
    private String withEmail;
    @NotBlank(message="credentials should be entered")
    private String email_password;
    private String pinAccount;
    private String colored;
    public Account buildAccount(User user){
        Account account=new Account();
        account.getAccountInfo().setIncome(income);
        account.getAccountInfo().setExpenses(expenses);
        account.getAccountInfo().setPrice(price);
        account.getAccountInfo().setUrl(url);
        account.getAccountInfo().setText(text);
        account.getAccountInfo().setHeader(header);
        account.getAccountInfo().setType(MediaType.valueOf(mediaType));
        account.setUserInfo(user.getUserInfo());
        account.getAccountInfo().setActivationCode("code");
        account.getAccountInfo().setFollowers(1233);
        account.getAccountInfo().setAccountStatus(AccountStatus.ON_SALE);
        account.getAccountInfo().setLookUps(0);
        account.setCategory(AccountCategory.inst);
        account.setPin(!pinAccount.equals("No"));
        account.setColored(!colored.equals("No"));
        account.setWithEmail(withEmail.equals("yes"));
        String [] emailAndPassword;
        emailAndPassword=email_password.split(":");
        account.setEmail(emailAndPassword[0]);
        account.setPassword(emailAndPassword[1]);
        return account;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getExpenses() {
        return expenses;
    }

    public void setExpenses(Integer expenses) {
        this.expenses = expenses;
    }

    public String getWithEmail() {
        return withEmail;
    }

    public void setWithEmail(String withEmail) {
        this.withEmail = withEmail;
    }

    public String getEmail_password() {
        return email_password;
    }

    public void setEmail_password(String email_password) {
        this.email_password = email_password;
    }

    public String getPinAccount() {
        return pinAccount;
    }

    public void setPinAccount(String pinAccount) {
        this.pinAccount = pinAccount;
    }

    public String getColored() {
        return colored;
    }

    public void setColored(String colored) {
        this.colored = colored;
    }
}
