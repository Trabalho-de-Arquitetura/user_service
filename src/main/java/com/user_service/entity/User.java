package com.user_service.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String affiliatedSchool;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    public User() {}

    public User(UUID id, String name, String email, String affiliatedSchool, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.affiliatedSchool = affiliatedSchool;
        this.role = role;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAffiliatedSchool() { return affiliatedSchool; }
    public void setAffiliatedSchool(String affiliatedSchool) { this.affiliatedSchool = affiliatedSchool; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}