package com.saraiev.adboard.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
