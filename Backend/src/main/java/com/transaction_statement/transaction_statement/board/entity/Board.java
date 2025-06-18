package com.transaction_statement.transaction_statement.board.entity;

import com.transaction_statement.transaction_statement.board.dto.CreateBoadrdRequestDto;
import com.transaction_statement.transaction_statement.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User writer;

    @NotBlank(message = "제목은 필수입니다.")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    @Builder
    public Board(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public static Board from(CreateBoadrdRequestDto dto, User user) {
        return Board.builder()
                .writer(user) // User 엔티티 객체
                .title(dto.getTitle())
                .contents(dto.getContents())
                .build();
    }
}
