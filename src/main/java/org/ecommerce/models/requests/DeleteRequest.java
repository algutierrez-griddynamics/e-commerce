package org.ecommerce.models.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteRequest<ID> {
    private ID id;
}
