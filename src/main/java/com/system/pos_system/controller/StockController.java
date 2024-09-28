package com.system.pos_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.dto.StockReqDTO;
import com.system.pos_system.entity.Item;
import com.system.pos_system.entity.Stock;
import com.system.pos_system.service.ItemService;
import com.system.pos_system.service.StockService;

@RestController
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockController {
    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock() {
        return ResponseEntity.status(200).body(stockService.getAllStock());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockByItemId(@PathVariable Long id) {
        Stock stock = stockService.getStockById(id);
        if (stock == null) {
            return ResponseEntity.status(404).body("Stock Not Found!");
        }
        return ResponseEntity.status(200).body(stock);
    }

    @PostMapping
    public ResponseEntity<String> createStock(@RequestBody StockReqDTO stockReqDTO) {
        if (stockReqDTO.getItemId() == null) {
            return ResponseEntity.status(422).body("Stock Item ID Missing!");
        }
        if (stockReqDTO.getQty() < 0) {
            return ResponseEntity.status(422).body("Stock Qty Invalid!");
        }
        Stock stock = new Stock();
        stock.setQty(stockReqDTO.getQty());
        Item item = itemService.getItemById(stockReqDTO.getItemId());
        stock.setItem(item);

        stockService.addStock(stock);
        return ResponseEntity.status(201).body("Stock Added!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        if (stockService.getStockById(id) == null) {
            return ResponseEntity.status(404).body("Stock Not Found!");
        }
        stockService.deleteStock(id);
        return ResponseEntity.status(201).body("Stock Deleted!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestBody StockReqDTO stockReqDTO) {
        if (stockService.getStockById(id) == null) {
            return ResponseEntity.status(404).body("Stock Not Found!");
        }
        Stock updatedStock = new Stock();
        updatedStock.setQty(stockReqDTO.getQty());
        stockService.updateStock(id, updatedStock);
        return ResponseEntity.status(200).body("Stock Updated!");
    }
}
