package org.ecommerce.services.jpa;

import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.CreateOrderResponse;
import org.ecommerce.models.services.responses.GetAllOrdersResponse;
import org.ecommerce.models.services.responses.GetOrderResponse;
import org.ecommerce.models.services.responses.UpdateOrderResponse;
import org.ecommerce.util.database.specifications.SpecificationParameters;
import org.springframework.data.domain.Pageable;

public interface OrderJpaService <REQUEST, ID> {

    CreateOrderResponse create(CreateRequest<REQUEST> entity);

    UpdateOrderResponse update(UpdateRequest<REQUEST, ID> entity);

    void delete(ID id);

    GetAllOrdersResponse findAll(SpecificationParameters specificationParameters, Pageable pageable);

    GetOrderResponse findById(ID id);
}
