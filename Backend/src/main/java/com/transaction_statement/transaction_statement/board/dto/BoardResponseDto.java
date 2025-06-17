package com.transaction_statement.transaction_statement.board.dto;

import com.transaction_statement.transaction_statement.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public static BoardResponseDto from(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getWriter(),
                board.getTitle(),
                board.getContents(),
                board.getCreatedDate(),
                board.getUpdatedDate()
        );
    }
}
