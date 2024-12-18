package com.spring.securityadvanced.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SubscriberSecurity implements UserDetails {

    private Subscriber subscriber;

    @Autowired
    public SubscriberSecurity(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(subscriber.getRole()));
        return authorityList;
    }
    @Override
    public String getPassword() {
        return subscriber.getPassword();
    }
    @Override
    public String getUsername() {
        return subscriber.getEmail();
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
}
