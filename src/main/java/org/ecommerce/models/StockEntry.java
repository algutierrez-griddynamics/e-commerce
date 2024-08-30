package org.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockEntry <ProductID, LocationID> {
    private ProductID productId;
    private LocationID locationId;
    private int stock;
}
