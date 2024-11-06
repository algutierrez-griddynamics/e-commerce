package org.ecommerce.services.jpa.impl;

import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.*;
import org.springframework.stereotype.Service;

@Service
public interface OrderJpaService <REQUEST, ID> {

    CreateOrderResponse create(CreateRequest<REQUEST> entity);

    UpdateOrderResponse update(UpdateRequest<REQUEST, ID> entity);

    void delete(ID id);

    GetAllOrdersResponse findAll();

    GetOrderResponse findById(ID id);
}
