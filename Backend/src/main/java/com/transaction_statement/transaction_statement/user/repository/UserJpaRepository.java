package com.transaction_statement.transaction_statement.user.repository;

import com.transaction_statement.transaction_statement.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 실제 DB에 접근하는 JPA 리포지토리
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}