package com.transaction_statement.transaction_statement.board.service;

import com.transaction_statement.transaction_statement.board.dto.BoardResponseDto;
import com.transaction_statement.transaction_statement.board.dto.CreateBoadrdRequestDto;
import com.transaction_statement.transaction_statement.board.entity.Board;
import com.transaction_statement.transaction_statement.board.repository.BoardRepository;
import com.transaction_statement.transaction_statement.user.entity.User;
import com.transaction_statement.transaction_statement.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public BoardResponseDto createBoard(CreateBoadrdRequestDto dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // 현재 인증된 사용자의 이메일
        
        // 이메일로 User 엔티티 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Board board = Board.from(dto, user);
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
