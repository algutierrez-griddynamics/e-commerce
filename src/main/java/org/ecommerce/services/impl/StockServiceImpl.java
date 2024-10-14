package org.ecommerce.services.impl;

import org.ecommerce.models.StockEntry;
import org.ecommerce.repositories.inmemory.StockRepository;
import org.ecommerce.services.StockService;

public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void addStockEntry(StockEntry<Long, Long> stockEntry) {
        stockRepository.addStockEntry(stockEntry);
    }

    @Override
    public StockEntry<Long, Long> getStockEntry(Long productId, Long locationId) {
        return stockRepository.getStockEntry(productId, locationId);
    }

    @Override
    public void updateStockEntry(StockEntry<Long, Long> stockEntry) {
        stockRepository.updateStockEntry(stockEntry);
    }
}

