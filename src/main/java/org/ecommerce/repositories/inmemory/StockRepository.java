package org.ecommerce.repositories.inmemory;

import org.ecommerce.models.StockEntry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockRepository {
    private final Map<Long, Map<Long, StockEntry<Long, Long>>> stockStore = new HashMap<>();

    public void addStockEntry(StockEntry<Long, Long> stockEntry) {
        stockStore
                .computeIfAbsent(stockEntry.getProductId() , k -> new HashMap<>())
                .put(stockEntry.getLocationId(), stockEntry);
    }

    public StockEntry<Long, Long> getStockEntry(Long productId, Long locationId) {
        return stockStore.getOrDefault(productId, Collections.emptyMap()).get(locationId);
    }

    public void updateStockEntry(StockEntry<Long, Long> stockEntry) {
        if (stockStore.containsKey(stockEntry.getProductId())) {
            stockStore.get(stockEntry.getProductId()).put(stockEntry.getLocationId(), stockEntry);
        }
    }
}
