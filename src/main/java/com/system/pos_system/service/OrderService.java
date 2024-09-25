package com.system.pos_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.pos_system.entity.Order;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    Order createOrder(Order order);

    Order getOrderById(Long id);

    void deleteOrder(Long id);
}
