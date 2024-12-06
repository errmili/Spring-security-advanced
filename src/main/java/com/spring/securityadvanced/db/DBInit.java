package com.spring.securityadvanced.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.securityadvanced.dao.SubscriberRepo;
import com.spring.securityadvanced.model.Subscriber;


@Service
public class DBInit implements CommandLineRunner {

    private final SubscriberRepo subscriberRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public DBInit(SubscriberRepo subscriberRepo, BCryptPasswordEncoder passwordEncoder) {
        this.subscriberRepo = subscriberRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
//        if (subscriberRepo.count() == 0) {
//            // Créer des utilisateurs avec des mots de passe encodés
//            Subscriber admin = new Subscriber(
//                    "admin@gmail.com",
//                    "admin",
//                    passwordEncoder.encode("admin123"),
//                    "ROLE_ADMIN"
//            );
//
//            Subscriber user = new Subscriber(
//                    "user@gmail.com",
//                    "user",
//                    passwordEncoder.encode("user123"),
//                    "ROLE_USER"
//            );
//
//            // Sauvegarder les utilisateurs dans la base de données
//            subscriberRepo.save(admin);
//            subscriberRepo.save(user);
//
//            System.out.println("Database initialized with default users.");
//        } else {
//            System.out.println("Database already contains users. Skipping initialization.");
//        }
    }
}
