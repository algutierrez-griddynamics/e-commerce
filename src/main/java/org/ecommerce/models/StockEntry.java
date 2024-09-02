package org.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockEntry <ProductID, LocationID> {
    private ProductID productId;
    private LocationID locationId;
    private int stock;
}
