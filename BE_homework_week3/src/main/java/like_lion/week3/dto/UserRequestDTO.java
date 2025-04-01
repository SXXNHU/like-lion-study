package like_lion.week3.dto;

import lombok.Getter;

@Getter
public class UserRequestDTO {

    private final String name;
    private final long age;
    private final String gender;

    public UserRequestDTO(String name, long age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}