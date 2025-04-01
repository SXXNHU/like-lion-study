package com.weektwo.week_two.service;

import com.weektwo.week_two.dto.UserDTO;
import com.weektwo.week_two.dto.UserRequestDTO;
import com.weektwo.week_two.dto.UserResponseDTO;
import com.weektwo.week_two.model.User;
import com.weektwo.week_two.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 생성
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getCreatedAt());
        userRepository.save(user);
        return userDTO;
    }

    // 사용자 조회
    public UserDTO getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // User의 데이터를 이용하여 UserDTO 객체 생성
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    // 사용자 정보 수정
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.updateUser(userDTO.getName(), userDTO.getEmail());
        userRepository.save(user);
        userDTO.setId(userId);
        return userDTO;
    }

    // 사용자 삭제
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
