package org.ecommerce.models.services.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class PageableModel {
    private int currentPage;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
