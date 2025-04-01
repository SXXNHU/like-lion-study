package com.weektwo.week_two.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weektwo.week_two.dto.BoardDTO;
import com.weektwo.week_two.dto.BoardDTO;
import com.weektwo.week_two.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService BoardService;

    @Test
    public void testCreateBoard() throws Exception {
        System.out.println("Running test: testCreateBoard");

        // BoardDTO 객체 생성
        BoardDTO boardDTO = new BoardDTO("12345", "테스트 게시글", "예시 내용", "작가", "2025-03-26");

        // BoardDTO를 JSON 형식으로 변환
        String BoardJson = new ObjectMapper().writeValueAsString(boardDTO);

        // BoardService의 createBoard 메서드가 BoardDTO를 반환하도록 설정
        when(BoardService.createBoard(boardDTO)).thenReturn(boardDTO);

        // MockMvc로 POST 요청을 보내고 응답을 확인
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(BoardJson))  // JSON 본문을 사용
                .andExpect(status().isCreated())  // 201 Created 상태 코드 확인
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // 생성된 응답 내용 출력
        System.out.println("Response: " + response);
       System.out.println("Completed test: testCreateBoard");
    }

    @Test
    public void testGetBoard() throws Exception {
        System.out.println("Running test: testGetBoard");

        // 테스트용 데이터 준비
        String BoardId = "testBoardId";
        BoardDTO boardDTO = new BoardDTO("12345", "테스트 게시글", "예시 내용", "작가", "2025-03-26");

        // Mocking: BoardService.getBoard 호출에 대해 BoardDTO 반환하도록 설정
        when(BoardService.getBoard(BoardId)).thenReturn(boardDTO);

        // MockMvc로 GET 요청 보내고 검증
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/boards/" + BoardId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 200 반환 기대
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // 가져온 유저 정보 출력
        System.out.println("Response: " + response);
        System.out.println("Completed test: testGetBoard");
    }

    @Test
    public void testUpdateBoard() throws Exception {
        System.out.println("Running test: testUpdateBoard");

        // 테스트용 데이터
        String BoardId = "testBoard";
        BoardDTO updatedBoardDTO = new BoardDTO("12345", "테스트 게시글", "예시 내용", "작가", "2025-03-26");

        // JSON 형태로 변환할 데이터
        String updatedBoardJson = new ObjectMapper().writeValueAsString(updatedBoardDTO);

        // Mock 동작 설정
        when(BoardService.updateBoard(BoardId, updatedBoardDTO)).thenReturn(updatedBoardDTO);

        // MockMvc로 PUT 요청 및 검증
        String response = mockMvc.perform(MockMvcRequestBuilders.put("/boards/" + BoardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBoardJson)) // JSON 본문에 수정된 데이터 포함
                .andExpect(status().isOk()) // HTTP 200 반환 확인
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // 수정된 유저 정보 출력
        System.out.println("Response: " + response);
        System.out.println("Completed test: testUpdateBoard");
    }

    @Test
    public void testDeleteBoard() throws Exception {
        System.out.println("Running test: testDeleteBoard");

        // 테스트용 데이터 ID
        String BoardId = "testBoard";

        // 테스트 수행: DELETE 요청
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/boards/" + BoardId))
                .andExpect(status().isNoContent()) // HTTP 204 반환 확인
                .andReturn()
                .getResponse()
                .getContentAsString(); // 응답 내용 가져오기

        // DELETE 요청 결과 출력 (빈 응답일 가능성이 큼)
        System.out.println("Response: " + response);
        System.out.println("Completed test: testDeleteBoard");
    }
}