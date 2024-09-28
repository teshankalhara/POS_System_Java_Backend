package com.system.pos_system.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.dto.OrderReqDTO;
import com.system.pos_system.entity.Order;
import com.system.pos_system.entity.OrderItem;
import com.system.pos_system.entity.Stock;
import com.system.pos_system.service.OrderService;
import com.system.pos_system.service.StockService;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.status(200).body(order);
        }
        return ResponseEntity.status(404).body("Order Not Found!");
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderReqDTO orderReqDTO) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for (OrderReqDTO.OrderItemDTO itemDto : orderReqDTO.getOrderItems()) {
            if (itemDto.getItemId() == null || itemDto.getQuantity() == null || itemDto.getQuantity() <= 0) {
                return ResponseEntity.status(400).body("Invalid item ID or quantity!");
            }

            Stock stock = stockService.getStockByItemId(itemDto.getItemId());
            if (stock == null) {
                return ResponseEntity.status(404).body("Item not found for ID: " + itemDto.getItemId());
            }

            if (stock.getQty() < itemDto.getQuantity()) {
                return ResponseEntity.status(400).body("Insufficient stock for item ID: " + itemDto.getItemId());
            }

            stock.setQty(stock.getQty() - itemDto.getQuantity());
            stockService.updateStock(stock.getId(), stock);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(stock.getItem());
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(stock.getItem().getPrice());

            orderItems.add(orderItem);

            totalPrice += stock.getItem().getPrice() * itemDto.getQuantity();
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        Order savedOrder = orderService.createOrder(order);

        return ResponseEntity.status(201).body(savedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            orderService.deleteOrder(id);
            return ResponseEntity.status(200).body("Order Deleted!");
        }
        return ResponseEntity.status(404).body("Order Not Found!");
    }
}
