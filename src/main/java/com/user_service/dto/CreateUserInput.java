package com.user_service.dto;

import com.user_service.entity.UserRole;

public class CreateUserInput {
    public String name;
    public String email;
    public String affiliatedSchool;
    public String password;
    public UserRole role;

    public CreateUserInput(String name, String email, String affiliatedSchool, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.affiliatedSchool = affiliatedSchool;
        this.password = password;
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
