package com.transaction_statement.transaction_statement.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Email {
    @Column(name = "email")
    private String value;

    public Email(String value) {
        if (!value.matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,3}$")) {
            throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다.");
        }
        this.value = value;
    }
}
