package org.ecommerce.services.jpa;

import org.ecommerce.models.Product;
import org.ecommerce.models.StockEntry;
import org.ecommerce.services.OperationsService;

public interface StockServiceI extends OperationsService<StockEntry, Long> {
    int getStockOfProduct(Product product);
    void setStockOfProduct(Product product, int stock);
}
