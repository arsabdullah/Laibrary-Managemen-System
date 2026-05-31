package com.library.model;

public abstract class User {
    private final String id;
    private String name;

    protected User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public abstract String getRole();

    public String getProfile() {
        return id + " | " + name + " | " + getRole();
    }
}
