package com.transaction_statement.transaction_statement.user.repository;

import com.transaction_statement.transaction_statement.user.domain.User;

public interface UserRepository {
    User findById(Long id);
    User save(User user);
}
