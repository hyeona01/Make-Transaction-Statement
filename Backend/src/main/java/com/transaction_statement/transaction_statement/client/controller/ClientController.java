package com.transaction_statement.transaction_statement.client.controller;

import com.transaction_statement.transaction_statement.client.dto.ClientRequestDto;
import com.transaction_statement.transaction_statement.client.dto.ClientResponseDto;
import com.transaction_statement.transaction_statement.client.service.ClientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientResponseDto> getClients(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        return clientService.getClientsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ClientResponseDto getClient(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        return clientService.getClientById(id, userId);
    }

    @PostMapping
    public ClientResponseDto createClient(@RequestBody ClientRequestDto clientRequestDto, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        return clientService.createClient(clientRequestDto, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = getUserId(userDetails);
        clientService.deleteClient(id, userId);
    }

    private Long getUserId(UserDetails userDetails) {
        return 1L; // 임시 구현
    }
}
