package com.weektwo.week_two.dto;

import lombok.Getter;

@Getter
public class UserResponseDTO {

    private final String username;
    private final String userpart;
    private final String message;

    public UserResponseDTO(String username, String userpart, String message) {
        this.username = username;
        this.userpart = userpart;
        this.message = message;
    }
}