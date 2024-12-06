package com.spring.securityadvanced.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.securityadvanced.model.Subscriber;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    public CustomAuthenticationProvider(SubscriberRepo subscriberRepo, PasswordEncoder passwordEncoder) {
//        this.subscriberRepo = subscriberRepo;
//        this.passwordEncoder = passwordEncoder;
//    }

    private SubscriberRepo subscriberRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationProvider(SubscriberRepo subscriberRepo, BCryptPasswordEncoder passwordEncoder) {
        this.subscriberRepo = subscriberRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName(); // true
        String password = authentication.getCredentials().toString();
        Optional<Subscriber> subscribers = subscriberRepo.findByEmail(userName);
        if(subscribers.isEmpty()){
            throw new BadCredentialsException("Invalid User you must be register");
        } else {
            if (passwordEncoder.matches(password,subscribers.get().getPassword())){
                List<GrantedAuthority> authorityList = new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority(subscribers.get().getRole()));
                return new UsernamePasswordAuthenticationToken(userName,password,authorityList);
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        }
    }

//    private List<SimpleGrantedAuthority> getAuthority(List<Authority> authorityList){
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        for (Authority authority: authorityList){ //
//            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//        }
//        return simpleGrantedAuthorities;
//    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
