package com.spring.securityadvanced.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name; //
    @ManyToOne
    @JoinColumn(name = "sub_id")
    private Subscriber subscriber;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Subscriber getSubscriber() {
        return subscriber;
    }
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}