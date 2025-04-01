package like_lion.week3.dto;

import lombok.Getter;

@Getter
public class UserResponseDTO {

    private final String name;
    private final long age;
    private final String gender;
    private final String message;

    public UserResponseDTO(String name, long age, String gender, String message) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.message = message;
    }
}