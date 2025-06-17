package com.transaction_statement.transaction_statement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequestDto {
    private String username;
    private String password;
    private String companyName;
    private String businessStatus;
    private String businessCategory;

    private String email;
    private String businessNumber;
    private String address;
}
