package com.transaction_statement.transaction_statement.client.repository;

import com.transaction_statement.transaction_statement.client.entity.Client;
import com.transaction_statement.transaction_statement.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    
    private final ClientJpaRepository clientJpaRepository;
    
    public ClientRepositoryImpl(ClientJpaRepository clientJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
    }
    
    @Override
    public Client save(Client client) {
        return clientJpaRepository.save(client);
    }
    
    @Override
    public Optional<Client> findById(Long id) {
        return clientJpaRepository.findById(id);
    }
    
    @Override
    public List<Client> findByUser(User user) {
        return clientJpaRepository.findByUser(user);
    }
    
    @Override
    public List<Client> findByUserIdOrderByNameAsc(Long userId) {
        return clientJpaRepository.findByUserIdOrderByNameAsc(userId);
    }
    
    @Override
    public boolean existsByNameAndUserId(String name, Long userId) {
        return clientJpaRepository.existsByNameAndUserId(name, userId);
    }
    
    @Override
    public void delete(Client client) {
        clientJpaRepository.delete(client);
    }
}
