package com.transaction_statement.transaction_statement.user.service;

import com.transaction_statement.transaction_statement.user.domain.User;
import com.transaction_statement.transaction_statement.user.dto.UserRequestDto;
import com.transaction_statement.transaction_statement.user.dto.UserResponseDto;
import com.transaction_statement.transaction_statement.user.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto registerUser(UserRequestDto dto){
        // Entity 변환
        User user = User.from(dto);

        // 이메일 중복 확인
        userRepository.findByEmail(String.valueOf(dto.getEmail())).ifPresent(user1 -> {
            throw new IllegalArgumentException("이미 등록된 이메일입니다.");
        });

        // 저장
        User saved = userRepository.save(user);

        // 응답 DTO 변환
        return UserResponseDto.from(saved);
    }

    public UserResponseDto getUser(Long id){
        // 존재하는지 확인
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 응답 DTO 변환
        return UserResponseDto.from(user);
    }
}
