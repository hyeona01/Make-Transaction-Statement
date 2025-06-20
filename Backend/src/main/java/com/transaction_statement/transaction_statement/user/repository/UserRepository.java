package com.transaction_statement.transaction_statement.user.repository;

import com.transaction_statement.transaction_statement.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
