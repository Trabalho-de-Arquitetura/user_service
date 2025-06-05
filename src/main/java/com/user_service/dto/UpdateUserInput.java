package com.user_service.dto;

import com.user_service.entity.UserRole;

import java.util.UUID;

public class UpdateUserInput {
    public UUID id;
    public String name;
    public String email;
    public String affiliatedSchool;
    public String password;
    public UserRole role;

    public UpdateUserInput(UUID id, String name, String email, String affiliatedSchool, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.affiliatedSchool = affiliatedSchool;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getAffiliatedSchool() {
        return affiliatedSchool;
    }

    public void setAffiliatedSchool(String affiliatedSchool) {
        this.affiliatedSchool = affiliatedSchool;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
