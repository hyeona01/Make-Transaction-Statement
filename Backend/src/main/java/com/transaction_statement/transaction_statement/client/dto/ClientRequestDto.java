package com.transaction_statement.transaction_statement.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

    private String name;
    private String businessNumber;
    private String address;
    private String tel;
    private String email;
    private String representativeName;
}
