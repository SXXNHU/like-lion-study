package like_lion.week3.service;

import like_lion.week3.dto.UserRequestDTO;
import like_lion.week3.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    //사용자 리스트
    private final List<UserRequestDTO> userList = new ArrayList<>();
    String save_message = "사용자 저장에 성공했습니다";
    String get_message = "사용자 조회에 성공했습니다";

    // 사용자 저장
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        userList.add(userRequestDTO);
        return new UserResponseDTO(userRequestDTO.getName(), userRequestDTO.getAge(), userRequestDTO.getGender(),save_message);
    }

    // 전체 사용자 조회
    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> responseDTOS =new ArrayList<>();
        for (UserRequestDTO userRequestDTO : userList) {
            responseDTOS.add(new UserResponseDTO(userRequestDTO.getName(), userRequestDTO.getAge(), userRequestDTO.getGender(), get_message));
        }
        return responseDTOS;
    }
}
