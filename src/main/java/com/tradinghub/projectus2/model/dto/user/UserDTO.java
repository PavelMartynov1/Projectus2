package com.tradinghub.projectus2.model.dto.user;

import com.tradinghub.projectus2.model.user.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank(message ="Please enter your Name")
    private String name;
    @Email(message = "PLease enter valid email")
    private String email;
    @NotBlank(message = "Please enter your userName")
    private String username;
    @NotBlank(message = "Please enter your password")
    private String password;

    public UserDTO() {
    }

    public User build(){
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        return user;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
