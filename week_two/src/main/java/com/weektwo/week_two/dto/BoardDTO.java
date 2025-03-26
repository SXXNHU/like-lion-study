package com.weektwo.week_two.dto;

import com.weektwo.week_two.model.Board;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private String boardId, title, content, writer, createdAt;

    public BoardDTO(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.createdAt = board.getCreatedAt();
    }
}