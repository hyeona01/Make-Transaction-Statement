package com.transaction_statement.transaction_statement.user.controller;

import com.transaction_statement.transaction_statement.user.dto.UserRequestDto;
import com.transaction_statement.transaction_statement.user.dto.UserResponseDto;
import com.transaction_statement.transaction_statement.user.repository.UserRepository;
import com.transaction_statement.transaction_statement.user.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto dto){
        User user = User.from(dto);
        User saved = userRepository.save(user);
        return UserResponseDto.from(saved);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        return UserResponseDto.from(user);
    }
}
