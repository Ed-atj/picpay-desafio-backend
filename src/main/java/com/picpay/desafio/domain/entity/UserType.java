package com.picpay.desafio.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_type")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public enum Enum {
        USER(1L, "user"),
        SHOPKEEPER(2L, "shopkeeper");

        private Long id;
        private String description;

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }
        public UserType get() {
            return new UserType(id, description);
        }
        @Override
        public String toString(){
            return description;
        }
    }

    public static UserType fromString(String value) {
        return switch (value) {
            case "USER" -> Enum.USER.get();
            case "SHOPKEEPER" -> Enum.SHOPKEEPER.get();
            default -> throw new IllegalArgumentException("Invalid UserType value: " + value);
        };
    }
}

