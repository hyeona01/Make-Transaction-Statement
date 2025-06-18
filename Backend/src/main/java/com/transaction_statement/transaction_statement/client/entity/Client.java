package com.transaction_statement.transaction_statement.client.entity;

import com.transaction_statement.transaction_statement.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "business_number")
    private String businessNumber;
    
    private String address;
    
    private String tel;
    
    private String email;
    
    @Column(name = "representative_name")
    private String representativeName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
