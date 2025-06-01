package com.user_service.dto;

import com.user_service.entity.UserRole;

public class CreateUserInput {
    public String name;
    public String email;
    public String affiliatedSchool;
    public UserRole role;

    public CreateUserInput(String name, String email, String affiliatedSchool, UserRole role) {
        this.name = name;
        this.email = email;
        this.affiliatedSchool = affiliatedSchool;
        this.role = role;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
