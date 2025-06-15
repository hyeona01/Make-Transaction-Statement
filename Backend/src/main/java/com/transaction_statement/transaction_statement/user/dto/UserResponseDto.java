package com.transaction_statement.transaction_statement.user.dto;

import com.transaction_statement.transaction_statement.user.domain.Address;
import com.transaction_statement.transaction_statement.user.domain.BusinessNumber;
import com.transaction_statement.transaction_statement.user.domain.Email;
import com.transaction_statement.transaction_statement.user.domain.User;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private String name;
    private String companyName;
    private String businessStatus;
    private String businessCategory;

    @Embedded
    private Email email;

    @Embedded
    private BusinessNumber businessNumber;

    @Embedded
    private Address address;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(
                user.getName(),
                user.getCompanyName(),
                user.getBusinessStatus(),
                user.getBusinessCategory(),
                user.getEmail(),
                user.getBusinessNumber(),
                user.getAddress()
        );
    }
}
