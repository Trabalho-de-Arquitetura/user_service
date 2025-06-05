package com.user_service.dto;

import com.user_service.entity.User;

public class LoginReturnDTO {
    private User user;
    private String token;

    public LoginReturnDTO(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
