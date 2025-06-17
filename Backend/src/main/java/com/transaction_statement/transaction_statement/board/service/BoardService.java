package com.transaction_statement.transaction_statement.board.service;

import com.transaction_statement.transaction_statement.board.dto.BoardResponseDto;
import com.transaction_statement.transaction_statement.board.dto.CreateBoadrdRequestDto;
import com.transaction_statement.transaction_statement.board.entity.Board;
import com.transaction_statement.transaction_statement.board.repository.BoardRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(CreateBoadrdRequestDto dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // writer로 설정될 사용자 이름

        Board board = Board.from(dto, username);
        boardRepository.save(board);

        return BoardResponseDto.from(board);
    }

    public BoardResponseDto getBoard(Long id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return BoardResponseDto.from(board);
    }

    public List<BoardResponseDto> getAllBoard(){
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(BoardResponseDto::from)
                .toList();
    }
}
