package com.transaction_statement.transaction_statement.client.service;

import com.transaction_statement.transaction_statement.client.dto.ClientRequestDto;
import com.transaction_statement.transaction_statement.client.dto.ClientResponseDto;
import com.transaction_statement.transaction_statement.client.entity.Client;
import com.transaction_statement.transaction_statement.client.repository.ClientRepository;
import com.transaction_statement.transaction_statement.common.exception.ResourceNotFoundException;
import com.transaction_statement.transaction_statement.user.entity.User;
import com.transaction_statement.transaction_statement.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDto> getClientsByUserId(Long userId) {
        return clientRepository.findByUserIdOrderByNameAsc(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientResponseDto getClientById(Long clientId, Long userId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("거래처를 찾을 수 없습니다. " + clientId));

        // 권한 확인 - 해당 유저의 거래처인지 확인
        if (!client.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("해당 거래처 접근 권한이 없습니다.");
        }

        return convertToDto(client);
    }

    @Transactional
    public ClientResponseDto createClient(ClientRequestDto clientRequestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다. " + userId));

        // 동일한 이름의 거래처가 이미 존재하는지 확인
        if (clientRepository.existsByNameAndUserId(clientRequestDto.getName(), userId)) {
            throw new IllegalArgumentException(clientRequestDto.getName() + "은(는) 이미 존재합니다.");
        }

        Client client = Client.builder()
                .name(clientRequestDto.getName())
                .businessNumber(clientRequestDto.getBusinessNumber())
                .address(clientRequestDto.getAddress())
                .tel(clientRequestDto.getTel())
                .email(clientRequestDto.getEmail())
                .representativeName(clientRequestDto.getRepresentativeName())
                .user(user)
                .build();

        Client savedClient = clientRepository.save(client);
        return convertToDto(savedClient);
    }

    @Transactional
    public void deleteClient(Long clientId, Long userId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다. " + clientId));

        // 권한 확인
        if (!client.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        clientRepository.delete(client);
    }

    private ClientResponseDto convertToDto(Client client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .businessNumber(client.getBusinessNumber())
                .address(client.getAddress())
                .tel(client.getTel())
                .email(client.getEmail())
                .representativeName(client.getRepresentativeName())
                .build();
    }
}
