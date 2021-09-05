package cn.bx.bbsdemo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oldliu
 * @since 1.0
 */
public class LoginUser implements Serializable, UserDetails {

    private User user;
    private List<GrantedAuthority> authorities=new ArrayList<>();

    public LoginUser(){

    }
    public LoginUser(User user){
        this.user=user;
    }
    public User getUser(){
        return this.user;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "user=" + user +
                ", authorities=" + authorities +
                '}';
    }
}
