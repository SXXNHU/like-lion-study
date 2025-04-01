package com.weektwo.week_two.model;

public class UserPR {

    private final String username;
    private final String userpart;

    public UserPR(String username, String userpart) {
        this.username = username;
        this.userpart = userpart;
    }

    public String getUsername() {
        return username;
    }
    public String getUserpart() {
        return userpart;
    }
}