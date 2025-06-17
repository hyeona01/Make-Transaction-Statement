package com.transaction_statement.transaction_statement.user.dto;

import com.transaction_statement.transaction_statement.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponseDto {
    private String username;
    private String companyName;
    private String businessStatus;
    private String businessCategory;

    private String email;
    private String businessNumber;
    private String address;

    public static SignupResponseDto from(User user) {
        return new SignupResponseDto(
                user.getUsername(),
                user.getCompanyName(),
                user.getBusinessStatus(),
                user.getBusinessCategory(),
                user.getEmail(),
                user.getBusinessNumber(),
                user.getAddress()
        );
    }
}
