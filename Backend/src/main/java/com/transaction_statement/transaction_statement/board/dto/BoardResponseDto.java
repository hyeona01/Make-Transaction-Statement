package com.transaction_statement.transaction_statement.board.dto;

import com.transaction_statement.transaction_statement.board.entity.Board;
import com.transaction_statement.transaction_statement.user.entity.User;
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
    
    /*
    // 추가 필드
    private Long writerId;
    private String writerEmail;
    private String writerName;
    private String companyName;
     */

    public static BoardResponseDto from(Board board) {
        User user = board.getWriter();
        return new BoardResponseDto(
                board.getId(),
                user.getUsername(),
                board.getTitle(),
                board.getContents(),
                board.getCreatedDate(),
                board.getUpdatedDate()
        );
    }
}
