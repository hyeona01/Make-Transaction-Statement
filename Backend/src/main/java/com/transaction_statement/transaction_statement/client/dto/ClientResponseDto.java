package com.transaction_statement.transaction_statement.client.dto;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {
    
    private Long id;
    private String name;
    private String businessNumber;
    private String address;
    private String tel;
    private String email;
    private String representativeName;
}
