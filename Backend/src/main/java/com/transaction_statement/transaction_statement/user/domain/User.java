package com.transaction_statement.transaction_statement.user.domain;

import com.transaction_statement.transaction_statement.user.dto.SignupRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name="user")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "대표자명은 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    private String password;

    @NotBlank(message = "회사명은 필수입니다.")
    private String companyName;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "사업자번호는 필수입니다.")
    @Pattern(regexp = "\\d{12}", message = "사업자번호는 숫자 10자리를 '-'로 구분하여 입력해야 합니다.")
    private String businessNumber;

    @NotBlank(message = "주소는 필수입니다.")
    private String address;

    private String businessStatus;
    private String businessCategory;

    private String role;

    public static User from(SignupRequestDto dto){
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCompanyName(dto.getCompanyName());
        user.setBusinessNumber(dto.getBusinessNumber());
        user.setAddress(dto.getAddress());
        user.setBusinessStatus(dto.getBusinessStatus());
        user.setBusinessCategory(dto.getBusinessCategory());
        user.setRole("ROLE_ADMIN");
        return user;
    }
}
