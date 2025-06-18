package com.transaction_statement.transaction_statement.client.repository;

import com.transaction_statement.transaction_statement.client.entity.Client;
import com.transaction_statement.transaction_statement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 실제 DB에 접근하는 JPA 리포지토리
public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    List<Client> findByUser(User user);
    List<Client> findByUserIdOrderByNameAsc(Long userId);
    boolean existsByNameAndUserId(String name, Long userId);
}
