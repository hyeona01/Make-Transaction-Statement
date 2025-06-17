package com.transaction_statement.transaction_statement.board.controller;

import com.transaction_statement.transaction_statement.board.dto.BoardResponseDto;
import com.transaction_statement.transaction_statement.board.dto.CreateBoadrdRequestDto;
import com.transaction_statement.transaction_statement.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("")
    public BoardResponseDto createBoard(@RequestBody CreateBoadrdRequestDto dto){
        return boardService.createBoard(dto);
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @GetMapping("")
    public List<BoardResponseDto> getAllBoard(){
        return boardService.getAllBoard();
    }
}
