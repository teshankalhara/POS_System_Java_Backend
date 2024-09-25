package com.system.pos_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.pos_system.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
