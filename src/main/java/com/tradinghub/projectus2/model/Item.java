package com.tradinghub.projectus2.model;


import javax.persistence.*;

@Entity
public abstract class Item {
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Item() {
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
