package com.tradinghub.projectus2.model.dto.userInfo;

import com.tradinghub.projectus2.model.user.User;
import com.tradinghub.projectus2.model.user.UserInfo;

import javax.validation.constraints.Null;

public class UserInfoDTO {
    private String number;
    private String tg;
    private String firstName;
    @Null
    private User user;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public UserInfo build(UserInfo userInfo){
        userInfo.setUser(user);
        userInfo.setFirstName(firstName);
        userInfo.setNumber(number);
        userInfo.setTg(tg);
        return userInfo;
    }
}
