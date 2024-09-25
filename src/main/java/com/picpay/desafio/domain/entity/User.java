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

    @Column(name="cpf-cnpj", unique = true)
    private int document;

    @Column(name="password")
    private String pass;

    @Column(name="balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private enum UserType{
        COMMON,
        SHOPKEEPER;
    }
}
