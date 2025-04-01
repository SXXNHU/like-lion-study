package like_lion.week3.controller;

import like_lion.week3.dto.UserRequestDTO;
import like_lion.week3.dto.UserResponseDTO;
import like_lion.week3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //사용자 저장
    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.saveUser(userRequestDTO);
        //String message = "사용자 저장에 성공했습니다.";
        return ResponseEntity.status(HttpStatus.CREATED).build();
        //response에 데이터를 포함하지 않으므로 .body가 아닌 .build를 사용합니다.
    }

    // 전체 유저 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        if (users == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 반환
        }
        return ResponseEntity.ok(users); // HTTP 200 반환
    }
}