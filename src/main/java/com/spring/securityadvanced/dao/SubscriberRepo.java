package com.spring.securityadvanced.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.securityadvanced.model.Subscriber;

public interface SubscriberRepo extends JpaRepository<Subscriber,Long> {
//    List<Subscriber> findByEmail(String email);
    Optional<Subscriber> findByEmail(String email);
    Optional<Subscriber> findByUsername(String username);
}
