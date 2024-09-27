package org.ecommerce.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateRequest<T, ID> {
    private T data;
    private ID id;
}
