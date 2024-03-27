package com.dpco.business.dto;
// this is logindto
public class LoginDto {

    private String username;
    private String password;
    private long id;
    private String role;


    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDto() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
