package com.weektwo.week_two.dto;

import lombok.Getter;

public class UserRequestDTO {

    private final String username;
    private final String userpart;

    public UserRequestDTO(String username, String userpart) {
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