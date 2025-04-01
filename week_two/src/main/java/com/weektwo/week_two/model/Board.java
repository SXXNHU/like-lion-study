package com.weektwo.week_two.model;

import com.weektwo.week_two.dto.BoardDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Board {

    @Id
    private String boardId;
    private String title, content, writer, createdAt;

    public Board(String boardId, String title, String content, String writer, String createdAt) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    public void updateBoard(BoardDTO boardDTO) {
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }
}