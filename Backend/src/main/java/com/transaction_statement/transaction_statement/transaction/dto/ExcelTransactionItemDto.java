package com.transaction_statement.transaction_statement.transaction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExcelTransactionItemDto {
    private String name;
    private String specification;
    private Integer quantity;
    private Long unitPrice;
    private Long amount;
    private String remark;
}
