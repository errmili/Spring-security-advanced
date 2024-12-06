package com.spring.securityadvanced.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.securityadvanced.dao.SubscriberRepo;
import com.spring.securityadvanced.model.Subscriber;
import com.spring.securityadvanced.model.SubscriberSecurity;

public class CustomUserDetailsService implements UserDetailsService {

    private SubscriberRepo subscriberRepo;

    @Autowired
    public CustomUserDetailsService(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<Subscriber> subscribers = subscriberRepo.findByEmail(username);
        Optional<Subscriber> subscribers = subscriberRepo.findByEmail(username);
        if(subscribers.isEmpty()){
            System.out.println("User not found: " + username);
            throw  new UsernameNotFoundException("User Email not exist " + username);
        }
//        System.out.println("User found: " + subscribers.get(0).getEmail());
//        return new SubscriberSecurity(subscribers.get(0));

        Subscriber subscriber = subscribers.get();
        System.out.println("User found: " + subscriber.getEmail());
        return new SubscriberSecurity(subscriber);
    }
}
