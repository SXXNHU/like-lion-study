package com.weektwo.week_two.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

    private String id, title, content, writer;

    public BoardDTO() {
        this.id = "";
        this.title = "";
        this.content = "";
        this.writer = "";
    }


}
