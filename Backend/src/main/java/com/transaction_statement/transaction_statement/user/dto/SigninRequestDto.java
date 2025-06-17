package com.transaction_statement.transaction_statement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SigninRequestDto {
    private String email;
    private String password;
}
