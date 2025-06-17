package com.transaction_statement.transaction_statement.board.repository;

import com.transaction_statement.transaction_statement.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {
    Board save(Board board);
    Optional<Board> findById(Long id);
    List<Board> findAll();
}
