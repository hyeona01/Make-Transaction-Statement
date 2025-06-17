package com.transaction_statement.transaction_statement.user.entity;

import com.transaction_statement.transaction_statement.user.dto.SignupRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name="user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "대표자명은 필수입니다.")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Column(nullable = false)
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "회사명은 필수입니다.")
    @Column(nullable = false)
    private String companyName;

    @NotBlank(message = "이메일은 필수입니다.")
    @Column(nullable = false, unique = true)
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "사업자번호는 필수입니다.")
    @Column(nullable = false)
    @Pattern(regexp = "\\d{12}", message = "사업자번호는 숫자 10자리를 '-'로 구분하여 입력해야 합니다.")
    private String businessNumber;

    @NotBlank(message = "주소는 필수입니다.")
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String businessStatus;

    @Column(nullable = false)
    private String businessCategory;

    @Column(nullable = false)
    private String role;


    @Builder
    public User(String username, String password, String companyName, String email,
                String businessNumber, String address, String businessStatus,
                String businessCategory, String role) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.email = email;
        this.businessNumber = businessNumber;
        this.address = address;
        this.businessStatus = businessStatus;
        this.businessCategory = businessCategory;
        this.role = role;
    }

    public static User from(SignupRequestDto dto){
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .companyName(dto.getCompanyName())
                .email(dto.getEmail())
                .businessNumber(dto.getBusinessNumber())
                .address(dto.getAddress())
                .businessStatus(dto.getBusinessStatus())
                .businessCategory(dto.getBusinessCategory())
                .role("ROLE_ADMIN")
                .build();
    }
}
