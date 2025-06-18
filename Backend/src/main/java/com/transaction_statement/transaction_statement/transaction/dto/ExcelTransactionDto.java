package com.transaction_statement.transaction_statement.transaction.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class ExcelTransactionDto {
    private LocalDate transactionDate;
    private String clientName;
    private String businessNumber;
    private String representativeName;
    private Long totalAmount;
    private Long taxAmount;
    private List<ExcelTransactionItemDto> items;
}
