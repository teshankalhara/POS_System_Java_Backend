package com.system.pos_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.pos_system.entity.Stock;
import com.system.pos_system.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public Stock addStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Stock existingStock = stockRepository.findById(id).orElse(null);
        if (existingStock != null) {
            existingStock.setQty(stock.getQty());
            return stockRepository.save(existingStock);
        }
        return null;
    }

    @Override
    public Stock getStockById(Long itemId) {
        return stockRepository.findById(itemId).orElse(null);
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    public Stock getStockByItemId(Long itemId) {
        return stockRepository.findByItemId(itemId);
    }

}
