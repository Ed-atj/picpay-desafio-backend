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

    @Column(name="full_name", nullable = false)
    private String fullName;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="cpf_cnpj", unique = true, nullable = false)
    private int document;

    @Column(name="password", nullable = false)
    private String pass;

    @Column(name="balance", nullable = false)
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
    public void debit(BigDecimal value){
        this.balance = this.balance.subtract(value);
    }
    public void credit(BigDecimal value){
        this.balance = this.balance.add(value);
    }

    public boolean blockShopkeeperTransfer(){
        return this.userType.equals(UserType.Enum.SHOPKEEPER.get());
    }

    public boolean userNotEnoughBalance(BigDecimal value){
        return this.balance.doubleValue()<value.doubleValue();
    }
}
