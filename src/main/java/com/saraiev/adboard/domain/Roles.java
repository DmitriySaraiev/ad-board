package com.saraiev.adboard.domain;


public enum Roles {

    ADMIN("ADMIN"),
    USER("USER");

    public final String name;

    private Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
