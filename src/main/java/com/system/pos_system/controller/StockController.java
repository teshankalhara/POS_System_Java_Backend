package com.system.pos_system.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.dto.StockReqDTO;
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

    @PostMapping("/stock")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return ResponseEntity.status(201).body(stockService.addStock(stock));
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        if (stockService.getStockByItemId(id) == null) {
            return ResponseEntity.status(404).body("Stock Not Found!");
        }
        stockService.deleteStock(id);
        return ResponseEntity.status(201).body("Stock Deleted!");
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody StockReqDTO stockReqDTO) {
        if (stockService.getStockByItemId(id) == null) {
            return ResponseEntity.status(404).body("Stock Not Found!");
        }
        Stock updatedStock = new Stock();
        updatedStock.setQty(stockReqDTO.getQty());
        stockService.updateStock(id, updatedStock);
        return ResponseEntity.status(200).body("Stock Updated!");
    }
}
