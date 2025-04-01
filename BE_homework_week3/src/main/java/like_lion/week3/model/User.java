package like_lion.week3.model;

import lombok.Getter;

@Getter
public class User {

    private final String name;
    private final long age;
    private final String gender;

    public User(String name, long age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}