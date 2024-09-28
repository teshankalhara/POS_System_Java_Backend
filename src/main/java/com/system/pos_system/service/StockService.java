package com.system.pos_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.pos_system.entity.Stock;

@Service
public interface StockService {
    List<Stock> getAllStock();

    Stock addStock(Stock stock);

    Stock updateStock(Long id, Stock stock);

    Stock getStockByItemId(Long itemId);

    Stock getStockById(Long itemId);

    void deleteStock(Long id);
}
