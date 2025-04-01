package com.weektwo.week_two.model;
 import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users")
public class User {

    @Id
    private String id;
    private String name, email, createdAt;

    public User(String id, String name, String email, String createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    // User 업데이트 메서드
    public void updateUser(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
