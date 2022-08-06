package com.tradinghub.projectus2.model.user;

import com.tradinghub.projectus2.model.account.Account;

import javax.persistence.*;
import java.util.Base64;
import java.util.Set;

@Entity
@Table(name = "userInfo")
public class UserInfo {
    public UserInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number", unique = true)
    private String number;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "vk", unique = true)
    private String vk;
    @Column(name = "tg", unique = true)
    private String tg;
    @OneToOne(mappedBy = "userInfo")
    private User user;

    @OneToMany(mappedBy = "userInfo")
    private Set<Account> accounts;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] profilepic;

    private String imageBase64;

    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(profilepic);
    }

    public byte[] getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(byte[] profilepic) {
        this.profilepic = profilepic;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
