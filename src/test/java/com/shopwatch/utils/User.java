package com.shopwatch.utils;

public class User {
    // Fields - the data this class holds
    private String username;
    private String password;
    private String userType;

    //Constructor - how we create a User object
    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername(){

        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserType() {
        return this.userType;
    }
}
