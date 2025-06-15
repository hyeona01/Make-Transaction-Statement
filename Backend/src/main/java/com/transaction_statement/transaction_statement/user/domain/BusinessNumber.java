package com.transaction_statement.transaction_statement.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class BusinessNumber {

    @Column(name = "business_number")
    private String value;

    public BusinessNumber(String value) {
        if (!value.matches("^\\d{3}-\\d{2}-\\d{5}$")) {
            throw new IllegalArgumentException("올바르지 않은 사업자 등록번호 형식입니다. (e.g. 123-45-67890)");
        }
        this.value = value;
    }
}
