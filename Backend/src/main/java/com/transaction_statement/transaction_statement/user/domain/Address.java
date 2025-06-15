package com.transaction_statement.transaction_statement.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    @Column(name = "address")
    private String value;

    public Address(String value) {
        if (value == null || value.trim().length() < 5) {
            throw new IllegalArgumentException("주소는 5자 이상이어야 합니다.");
        }
        this.value = value;
    }
}
