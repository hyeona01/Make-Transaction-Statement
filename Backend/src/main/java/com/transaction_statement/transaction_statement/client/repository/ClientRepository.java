package com.transaction_statement.transaction_statement.client.repository;

import com.transaction_statement.transaction_statement.client.entity.Client;
import com.transaction_statement.transaction_statement.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(Long id);
    List<Client> findByUser(User user);
    List<Client> findByUserIdOrderByNameAsc(Long userId);
    boolean existsByNameAndUserId(String name, Long userId);
    void delete(Client client);
}
