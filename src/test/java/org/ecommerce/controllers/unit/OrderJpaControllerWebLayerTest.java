package org.ecommerce.controllers.unit;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.controllers.OrderJpaController;
import org.ecommerce.dtos.requests.OrderRequestDTO;
import org.ecommerce.dtos.responses.OrderDTO;
import org.ecommerce.enums.Error;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.mappers.*;
import org.ecommerce.models.Order;
import org.ecommerce.models.requests.CreateRequest;
import org.ecommerce.models.requests.UpdateRequest;
import org.ecommerce.models.services.responses.CreateOrderResponse;
import org.ecommerce.models.services.responses.UpdateOrderResponse;
import org.ecommerce.services.jpa.OrderJpaService;
import org.ecommerce.services.jpa.impl.OrderJpaServiceImpl;
import org.ecommerce.util.tests.OrderUtils;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(controllers = OrderJpaController.class
//        , excludeAutoConfiguration = SecurityAutoConfiguration.class
)
public class OrderJpaControllerWebLayerTest {

    private Order order;

    @MockBean
    private OrderJpaServiceImpl orderJpaService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerDTOMapper customerDTOMapper;

    // OrderDTOMapper dependencies
    @Mock
    private ProductsDTOMapper productsDTOMapper;
    @Mock
    private ShippingInformationDTOMapper shippingInformationDTOMapper;
    @Mock
    private BillingInformationDtoMapper billingInformationDtoMapper;
    @Mock
    private PaymentDetailsDTOMapper paymentDetailsDTOMapper;

    @InjectMocks
    private OrderDTOMapper orderDTOMapper;

    @InjectMocks
    private OrderRequestDTOMapper orderRequestDTOMapper;

    @BeforeEach
    void setup() {
        order = OrderUtils.buildOrder();
    }

    @DisplayName("Create an order when the request provided is valid, assert new DTO")
    @Test
    void testOrderCreatedSuccessfully_assertNewDTO() throws Exception {
        // Arrange
        OrderRequestDTO orderRequestDTO = orderRequestDTOMapper.apply(order);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderRequestDTO));

        OrderDTO orderDTO = orderDTOMapper.apply(order);

        when(orderJpaService.create(any(CreateRequest.class)))
                .thenReturn(new CreateOrderResponse(orderDTO));

        // Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();



        CreateOrderResponse createdOrderResponse
                = new ObjectMapper().readValue(responseBody, CreateOrderResponse.class);

        OrderDTO createdOrder = createdOrderResponse.getOrderDTO();



        // Assert
        Assertions.assertAll(
                () ->
                {
                    Assertions.assertEquals(order.getOrderDate()
                            , createdOrder.orderDate(), "Incorrect order date");
                    Assertions.assertEquals(order.getStatus()
                            , createdOrder.status(), "Incorrect order status");
                    Assertions.assertEquals(order.getTotalUsd()
                            , createdOrder.totalUsd(), "Incorrect total usd");
                }
        );

    }

    @DisplayName("Call Post /orders endpoint and assert valid code status (201)")
    @Test
    void testOrderCreatedSuccessfully_assertResponse() throws Exception {

        String orderJson = new ObjectMapper().writeValueAsString(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("Call Post /orders endpoint and assert invalid code status (400) when sending null in the for the Data in the request")
    @Test
    void testOrderCreatedUnsuccessfullyNullRequest_assertResponse() throws Exception {
        String orderJson = new ObjectMapper().writeValueAsString(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("Call Post /orders endpoint and assert invalid code status (400) when sending null Customer inside the DTO model")
    @Test
    void testOrderCreatedUnsuccessfullyNullDTO_assertResponse() throws Exception {
        order.setCustomer(null);
        OrderRequestDTO orderRequestDTO = null;
        
        try {
            orderRequestDTO = orderRequestDTOMapper.apply(order);
        } catch (Exception e) {}

        String orderJson = new ObjectMapper().writeValueAsString(orderRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DisplayName("Call delete method in the controller and return a 404 status code")
    @Test
    void testDeleteOrderUnsuccessfully() throws Exception {
        Long orderId = 999L;

        doThrow(EntityNotFound.class).when(orderJpaService).delete(orderId);

        mockMvc.perform(delete("/orders/{orderId}", orderId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @DisplayName("Call delete method in the controller and return a 204 status code")
    @Test
    void testDeleteOrderSuccessfully() throws Exception {
        Long orderId = 1L;

        doNothing().when(orderJpaService).delete(orderId);

        mockMvc.perform(delete("/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(orderJpaService, times(1)).delete(orderId);
    }

    @DisplayName("Call update method in the controller and return a 200 status code")
    @Test
    void testUpdateOrderSuccessfully() throws Exception {
        OrderRequestDTO orderRequestDTO = orderRequestDTOMapper.apply(order);
        UpdateRequest<OrderRequestDTO, Long> updateRequest
                = new UpdateRequest<>(orderRequestDTO, order.getId());

        OrderDTO orderDTO = orderDTOMapper.apply(order);

        when(orderJpaService.update(updateRequest))
                .thenReturn(new UpdateOrderResponse(orderDTO));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/orders", order.getId())
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateRequest));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Call update method in the controller and return a 400 status code")
    @Test
    void testUpdateOrderUnsuccessfully_badRequest() throws Exception {
        order.setCustomer(null);
        OrderRequestDTO orderRequestDTO = null;

        try {
            orderRequestDTO = orderRequestDTOMapper.apply(order);
        } catch (Exception e) {}

        String orderJson = new ObjectMapper().writeValueAsString(orderRequestDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/orders")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @DisplayName("Call update method in the controller and return a 404 status code")
    @Test
    void testUpdateOrderUnsuccessfully_entityNotFound() throws Exception {
        Long orderId = 1L;
        OrderRequestDTO orderRequestDTO = orderRequestDTOMapper.apply(order);
        UpdateRequest<OrderRequestDTO, Long> updateRequest
                = new UpdateRequest<>(orderRequestDTO, orderId);

        when(orderJpaService.update(any(UpdateRequest.class))).thenThrow(new EntityNotFound(Error.ENTITY_NOT_FOUND.getDescription()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/orders", orderId)
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateRequest));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
