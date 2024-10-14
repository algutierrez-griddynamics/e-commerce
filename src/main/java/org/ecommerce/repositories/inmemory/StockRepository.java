package org.ecommerce.repositories.inmemory;

import org.ecommerce.models.StockEntry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockRepository {
    private final Map<Long, Map<Long, StockEntry>> stockStore = new HashMap<>();

    public void addStockEntry(StockEntry stockEntry) {
        stockStore
                .computeIfAbsent(stockEntry.getProduct().getId() , k -> new HashMap<>())
                .put(stockEntry.getLocation().getId() , stockEntry);
    }

    public StockEntry getStockEntry(Long productId, Long locationId) {
        return stockStore.getOrDefault(productId, Collections.emptyMap()).get(locationId);
    }

    public void updateStockEntry(StockEntry stockEntry) {
        if (stockStore.containsKey(stockEntry.getProduct().getId())) {
            stockStore.get(stockEntry.getProduct().getId()).put(stockEntry.getLocation().getId(), stockEntry);
        }
    }
}
