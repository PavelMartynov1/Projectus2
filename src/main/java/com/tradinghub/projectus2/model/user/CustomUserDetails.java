package com.tradinghub.projectus2.model.user;

import com.tradinghub.projectus2.model.Permission;
import com.tradinghub.projectus2.model.Role;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="userDetails")
public class CustomUserDetails implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "userDetails")
    private User user;

    private boolean isAccountNonExpired=true;

    private boolean isAccountNonLocked=true;

    private boolean isEnabled=false;

    private boolean isCredentialsNonExpired=true;
    public User getUser() {
        return user;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role r: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
            for (Permission p: r.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
