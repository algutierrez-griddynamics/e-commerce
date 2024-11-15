package org.ecommerce.mappers;

import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.dtos.responses.ProductDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.MappingException;
import org.ecommerce.models.Order;
import org.ecommerce.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OrderDTOMapper implements Function<Order, OrderDTO> {

    private final CustomerDTOMapper customerDTOMapper;
    private final ProductsDTOMapper productsDTOMapper;
    private final ShippingInformationDTOMapper shippingInformationDTOMapper;
    private final BillingInformationDtoMapper billingInformationDtoMapper;
    private final PaymentDetailsDTOMapper paymentDetailsDTOMapper;

    public OrderDTOMapper(CustomerDTOMapper customerDTOMapper, ProductsDTOMapper productsDTOMapper, ShippingInformationDTOMapper shippingInformationDTOMapper,
                          BillingInformationDtoMapper billingInformationDTOMapper, PaymentDetailsDTOMapper paymentDetailsDTOMapper, PaymentDetailsDTOMapper paymentDetailsMapper) {
        this.customerDTOMapper = customerDTOMapper;
        this.productsDTOMapper = productsDTOMapper;
        this.shippingInformationDTOMapper = shippingInformationDTOMapper;
        this.billingInformationDtoMapper = billingInformationDTOMapper;
        this.paymentDetailsDTOMapper = paymentDetailsDTOMapper;
    }


    @Override
    public OrderDTO apply(Order order) {
        return Optional.ofNullable(order)
                .map(o -> new OrderDTO(
                        customerDTOMapper.apply(o.getCustomer()),
                        o.getOrderDate(),
                        o.getTotalUsd(),
                        o.getProducts().stream().map(productsDTOMapper).collect(Collectors.toList()),
                        o.getStatus(),
                        shippingInformationDTOMapper.apply(o.getShippingInformation()),
                        billingInformationDtoMapper.apply(o.getBillingInformation()),
                        paymentDetailsDTOMapper.apply(o.getPaymentDetails())
                )).orElseThrow(
                        () -> new MappingException(Error.MAPPING_EXCEPTION.getDescription())
                );
    }
}
