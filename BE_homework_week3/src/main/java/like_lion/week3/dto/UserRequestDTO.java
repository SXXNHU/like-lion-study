package like_lion.week3.dto;

import lombok.Getter;

@Getter
public class UserRequestDTO {

    private final String name;
    private final String id;

    public UserRequestDTO(String name, String id) {
        this.name = name;
        this.id = id;
    }
}