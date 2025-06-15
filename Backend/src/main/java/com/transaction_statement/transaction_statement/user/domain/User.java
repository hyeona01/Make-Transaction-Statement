package com.transaction_statement.transaction_statement.user.domain;

import com.transaction_statement.transaction_statement.user.dto.UserRequestDto;
import com.transaction_statement.transaction_statement.user.dto.UserResponseDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.springframework.data.annotation.Id;

@Entity(name="user")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;
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

    public static User from(UserRequestDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCompanyName(dto.getCompanyName());
        user.setBusinessNumber(dto.getBusinessNumber());
        user.setAddress(dto.getAddress());
        user.setBusinessStatus(dto.getBusinessStatus());
        user.setBusinessCategory(dto.getBusinessCategory());
        return user;
    }
}
