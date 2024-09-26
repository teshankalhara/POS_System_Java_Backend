package com.system.pos_system.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDateTime;

    @Column(nullable = false)
    private Double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @PrePersist
    protected void onCreate() {
        if (this.orderDateTime == null) {
            this.orderDateTime = LocalDateTime.now();
        }
    }
}
