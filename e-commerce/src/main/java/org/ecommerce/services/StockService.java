package org.ecommerce.services;

import org.ecommerce.models.StockEntry;

public interface StockService {
    void addStockEntry(StockEntry stockEntry);
    StockEntry getStockEntry(Long productId, Long locationId);
    void updateStockEntry(StockEntry stockEntry);
}
