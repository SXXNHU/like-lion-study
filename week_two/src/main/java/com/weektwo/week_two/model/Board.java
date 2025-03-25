package com.weektwo.week_two.model;

import com.weektwo.week_two.dto.BoardDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
public class Board {

    @Id
    @Getter @Setter
    private Long id;
    private String title;
    private String content;

    public Board(BoardDTO boardDTO) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }

    public void updateBoard(BoardDTO boardDTO) {
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
    }
}