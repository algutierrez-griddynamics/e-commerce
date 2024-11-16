package org.ecommerce.services.jpa.impl;

import org.ecommerce.models.Product;
import org.ecommerce.models.StockEntry;
import org.ecommerce.repositories.jpa.StockJpaRepository;
import org.ecommerce.services.jpa.StockServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockJpaServiceImpl implements StockServiceI {

    private StockJpaRepository stockJpaRepository;

    StockJpaServiceImpl(StockJpaRepository stockJpaRepository) {
        this.stockJpaRepository = stockJpaRepository;
    }


    @Override
    public StockEntry create(StockEntry entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StockEntry update(StockEntry entity, Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<StockEntry> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StockEntry findById(Long aLong) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getStockOfProduct(Product product) {
        return 10;
    }
}
