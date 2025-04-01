package com.weektwo.week_two.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weektwo.week_two.dto.UserDTO;
import com.weektwo.week_two.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        System.out.println("Running test: testCreateUser");

        // UserDTO 객체 생성
        UserDTO userDTO = new UserDTO("testUser", "테스트 유저", "test@example.com", "2025-03-25");

        // UserDTO를 JSON 형식으로 변환
        String userJson = new ObjectMapper().writeValueAsString(userDTO);

        // userService의 createUser 메서드가 userDTO를 반환하도록 설정
        when(userService.createUser(userDTO)).thenReturn(userDTO);

        // MockMvc로 POST 요청을 보내고 응답을 확인
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))  // JSON 본문을 사용
                .andExpect(status().isCreated())  // 201 Created 상태 코드 확인
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // 생성된 응답 내용 출력
        System.out.println("Response: " + response);
        System.out.println("Completed test: testCreateUser");
    }

    @Test
    public void testGetUser() throws Exception {
        System.out.println("Running test: testGetUser");

        // 테스트용 데이터 준비
        String userId = "testUser";
        UserDTO userDTO = new UserDTO(userId, "테스트 유저", "test@example.com", "2025-03-25");

        // Mocking: UserService.getUser 호출에 대해 userDTO 반환하도록 설정
        when(userService.getUser(userId)).thenReturn(userDTO);

        // MockMvc로 GET 요청 보내고 검증
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 200 반환 기대
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // 가져온 유저 정보 출력
        System.out.println("Response: " + response);
        System.out.println("Completed test: testGetUser");
    }

    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("Running test: testUpdateUser");

        // 테스트용 데이터
        String userId = "testUser";
        UserDTO updatedUserDTO = new UserDTO(userId, "수정된 유저", "updated@example.com", "2025-03-25");

        // JSON 형태로 변환할 데이터
        String updatedUserJson = new ObjectMapper().writeValueAsString(updatedUserDTO);

        // Mock 동작 설정
        when(userService.updateUser(userId, updatedUserDTO)).thenReturn(updatedUserDTO);

        // MockMvc로 PUT 요청 및 검증
        String response = mockMvc.perform(MockMvcRequestBuilders.put("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson)) // JSON 본문에 수정된 데이터 포함
                .andExpect(status().isOk()) // HTTP 200 반환 확인
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // 수정된 유저 정보 출력
        System.out.println("Response: " + response);
        System.out.println("Completed test: testUpdateUser");
    }

    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("Running test: testDeleteUser");

        // 테스트용 데이터 ID
        String userId = "testUser";

        // 테스트 수행: DELETE 요청
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + userId))
                .andExpect(status().isNoContent()) // HTTP 204 반환 확인
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // DELETE 요청 결과 출력 (빈 응답일 가능성이 큼)
        System.out.println("Response: " + response);
        System.out.println("Completed test: testDeleteUser");
    }
}