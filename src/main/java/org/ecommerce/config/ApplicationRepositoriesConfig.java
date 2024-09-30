package org.ecommerce.config;

import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationRepositoriesConfig {

    @Bean("UserRepository")
    UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    ManagerRepository managerRepository() {
        return new ManagerRepository();
    }

    @Bean
    OrderRepository orderRepository() {
        return new OrderRepository();
    }

    @Bean
    MessageQueue<?> messageQueue() {
        return new MessageQueue<>();
    }

    @Bean
    ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    StockRepository stockRepository() {
        return new StockRepository();
    }


}
