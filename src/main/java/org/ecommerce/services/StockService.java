package org.ecommerce.services;

import org.ecommerce.models.StockEntry;

public interface StockService {
    void addStockEntry(StockEntry<Long, Long> stockEntry);
    StockEntry<Long, Long> getStockEntry(Long productId, Long locationId);
    void updateStockEntry(StockEntry<Long, Long> stockEntry);
}
