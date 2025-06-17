package com.transaction_statement.transaction_statement.user.dto;

import com.transaction_statement.transaction_statement.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SigninResponseDto {
    private String username;
    private String email;

    public static SigninResponseDto from(User user) {
        return new SigninResponseDto(
                user.getUsername(),
                user.getEmail()
        );
    }
}
