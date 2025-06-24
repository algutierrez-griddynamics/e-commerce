package org.ecommerce.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetRequest<ID> {
    private ID id;
}
