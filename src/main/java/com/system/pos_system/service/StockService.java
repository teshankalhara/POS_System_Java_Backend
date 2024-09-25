package com.system.pos_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.system.pos_system.entity.Stock;

@Service
public interface StockService {
    List<Stock> getAllStock();

    Stock addStock(Stock stock);

    Stock updateStock(Long id, int newQuantity);

    Stock getStockByItemId(Long itemId);

    void deleteStock(Long id);
}
