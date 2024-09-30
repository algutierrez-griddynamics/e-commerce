package org.ecommerce.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAllRequest {
    private int page;
    private int size;
}
