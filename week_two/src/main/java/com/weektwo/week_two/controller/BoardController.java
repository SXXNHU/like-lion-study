package com.weektwo.week_two.controller;

import com.weektwo.week_two.dto.BoardDTO;
import com.weektwo.week_two.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        BoardDTO createdBoard = boardService.createBoard(boardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    @GetMapping("{boardId}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable String boardId) {
        BoardDTO boardDTO = boardService.getBoard(boardId);
        if (boardDTO == null) {
            return ResponseEntity.notFound().build(); //HTTP 404 반환
        }
        return ResponseEntity.status(HttpStatus.OK).body(boardDTO); //HTTP 200 반환
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable String boardId, @RequestBody BoardDTO boardDTO) {
        BoardDTO updatedBoard = boardService.updateBoard(boardId, boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable String boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }
}
