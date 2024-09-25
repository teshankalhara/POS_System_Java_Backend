package com.system.pos_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.entity.Stock;
import com.system.pos_system.service.StockService;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/stock")
    public ResponseEntity<List<Stock>> getAllStock() {
        return ResponseEntity.status(200).body(stockService.getAllStock());
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<?> getStockByItemId(@PathVariable Long id) {
        Stock stock = stockService.getStockByItemId(id);
        if (stock == null) {
            return ResponseEntity.status(404).body("Stock Not Found!");
        }
        return ResponseEntity.status(200).body(stock);
    }
}
