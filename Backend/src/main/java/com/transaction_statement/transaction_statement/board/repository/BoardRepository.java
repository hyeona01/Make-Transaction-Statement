package com.transaction_statement.transaction_statement.board.repository;

import com.transaction_statement.transaction_statement.board.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Board save(Board board);
    Optional<Board> findById(Long id);
    List<Board> findAll();
}
