package com.weektwo.week_two.service;

import com.weektwo.week_two.dto.BoardDTO;
import com.weektwo.week_two.model.Board;
import com.weektwo.week_two.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardDTO createBoard(BoardDTO boardDTO) {
        //비즈니스 로직 : 게시글 생성
        Board board = new Board(boardDTO.getBoardId(), boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter(), boardDTO.getCreatedAt());
        boardRepository.save(board);
        return boardDTO;
    }

    public BoardDTO getBoard(@PathVariable String boardId) {
        //비즈니스 로직 : 게시글 조회
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board not found"));
        return new BoardDTO(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getWriter(),
                board.getCreatedAt()
        );
    }

    public BoardDTO updateBoard(String boardId, BoardDTO boardDTO) {
        //비즈니스 로작 : 게시글 수정
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board not found"));
        board.updateBoard(boardDTO);
        boardRepository.save(board);
        return boardDTO;
    }

    public void deleteBoard(String boardId) {
        //비즈니스 로직 : 게시글 삭제
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board not found"));
        boardRepository.delete(board);
    }
}