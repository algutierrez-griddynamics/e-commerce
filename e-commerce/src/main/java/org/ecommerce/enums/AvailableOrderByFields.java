package org.ecommerce.enums;

import lombok.Getter;

@Getter
public enum AvailableOrderByFields {
    DATE("orderDate"),
    STATUS("status"),
    TOTAL("totalUsd");

    private final String fieldName;

    AvailableOrderByFields(String fieldName) {
        this.fieldName = fieldName;
    }

}

