package com.system.pos_system.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Order {
    private Long id;
    private LocalDateTime orderDateTime;
    private Double totalPrice;

}
