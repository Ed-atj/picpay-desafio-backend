package com.picpay.desafio.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="cpf_cnpj", unique = true)
    private int document;

    @Column(name="password")
    private String pass;

    @Column(name="balance")
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name="user_type_id")
    private UserType userType;

    public User(String fullName, String email, int document, String pass, BigDecimal balance, UserType userType) {
        this.fullName = fullName;
        this.email = email;
        this.document = document;
        this.pass = pass;
        this.balance = balance;
        this.userType = userType;
    }
}
