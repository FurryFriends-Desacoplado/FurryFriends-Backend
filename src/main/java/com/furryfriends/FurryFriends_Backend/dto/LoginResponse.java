package com.furryfriends.FurryFriends_Backend.dto;

public class LoginResponse {
    private String email;
    private String token;

    public LoginResponse(String email, String token) {
        this.email = email;
        this.token = token;
    }

    // Getters y setters
    public String getEmail() { return email; }
    public String getToken() { return token; }
}
