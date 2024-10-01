package com.picpay.desafio.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "transferences")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name="user_sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id")
    private User receiver;

    @Column(name="value")
    private BigDecimal value;

}
