package com.br.fiap.domain.model;

import java.time.OffsetDateTime;

public class UserDomain {

    private String id;
    private String name;
    private String password;
    private String username;
    private String email;
    private Boolean activeUser;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public UserDomain() {
    }

    public UserDomain(String username, String password, String name, String email, Boolean activeUser, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.activeUser = activeUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // GETTERS AND SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Boolean activeUser) {
        this.activeUser = activeUser;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
