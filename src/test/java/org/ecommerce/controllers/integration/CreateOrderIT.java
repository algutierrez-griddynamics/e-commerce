package org.ecommerce.controllers.integration;

import org.ecommerce.models.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateOrderIT {

    @Autowired
    MockMvc mockMvc;

    @Mock
    Order order;

    @Disabled
    @Test
    void createOrderSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/orders"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
