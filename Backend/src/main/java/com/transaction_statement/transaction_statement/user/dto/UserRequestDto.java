package com.transaction_statement.transaction_statement.user.dto;

import com.transaction_statement.transaction_statement.user.domain.Address;
import com.transaction_statement.transaction_statement.user.domain.BusinessNumber;
import com.transaction_statement.transaction_statement.user.domain.Email;
import com.transaction_statement.transaction_statement.user.domain.User;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestDto {
    private String name;
    private String password;
    private String companyName;
    private String businessStatus;
    private String businessCategory;

    @Embedded
    private Email email;

    @Embedded
    private BusinessNumber businessNumber;

    @Embedded
    private Address address;
}
