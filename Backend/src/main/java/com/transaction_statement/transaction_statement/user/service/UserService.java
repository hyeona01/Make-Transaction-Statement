package com.transaction_statement.transaction_statement.user.service;

import com.transaction_statement.transaction_statement.user.domain.User;
import com.transaction_statement.transaction_statement.user.dto.SigninRequestDto;
import com.transaction_statement.transaction_statement.user.dto.SigninResponseDto;
import com.transaction_statement.transaction_statement.user.dto.SignupRequestDto;
import com.transaction_statement.transaction_statement.user.dto.SignupResponseDto;
import com.transaction_statement.transaction_statement.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignupResponseDto registerUser(SignupRequestDto dto){
        // Entity 변환
        User user = User.from(dto);
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        // 이메일 중복 확인
        userRepository.findByEmail(String.valueOf(dto.getEmail())).ifPresent(user1 -> {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        });

        // 저장
        User saved = userRepository.save(user);

        // 응답 DTO 변환
        return SignupResponseDto.from(saved);
    }

    public SigninResponseDto getUser(SigninRequestDto dto){
        String email = dto.getEmail();
        String password = dto.getPassword();

        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호 일치 여부 확인
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 응답 DTO 반환
        return SigninResponseDto.from(user);
    }
}
