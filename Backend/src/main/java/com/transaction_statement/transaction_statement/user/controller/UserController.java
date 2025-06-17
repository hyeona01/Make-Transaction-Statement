package com.transaction_statement.transaction_statement.user.controller;

import com.transaction_statement.transaction_statement.user.dto.SigninRequestDto;
import com.transaction_statement.transaction_statement.user.dto.SigninResponseDto;
import com.transaction_statement.transaction_statement.user.dto.SignupRequestDto;
import com.transaction_statement.transaction_statement.user.dto.SignupResponseDto;
import com.transaction_statement.transaction_statement.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignupResponseDto createUser(@RequestBody SignupRequestDto dto){
        return userService.registerUser(dto);
    }

    @PostMapping("/signin")
    public SigninResponseDto getUser(@RequestBody SigninRequestDto dto){
        return userService.getUser(dto);
    }
}
