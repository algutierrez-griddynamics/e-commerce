package org.ecommerce.config;

import org.ecommerce.message.broker.MessageQueue;
import org.ecommerce.models.Manager;
import org.ecommerce.models.Order;
import org.ecommerce.models.Product;
import org.ecommerce.models.User;
import org.ecommerce.repositories.inmemory.*;
import org.ecommerce.util.database.Operations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Operations.class)
public class ApplicationRepositoriesConfig {

    @Bean("UserRepository")
    UserRepository userRepository(Operations<User> operations) {
        return new UserRepository(operations);
    }

    @Bean
    ManagerRepository managerRepository(Operations<Manager> operations) {
        return new ManagerRepository(operations);
    }

    @Bean
    OrderRepository orderRepository(Operations<Order> operations) {
        return new OrderRepository(operations);
    }

    @Bean
    MessageQueue<?> messageQueue() {
        return new MessageQueue<>();
    }

    @Bean
    ProductRepository productRepository(Operations<Product> operations) {
        return new ProductRepository(operations);
    }

    @Bean
    StockRepository stockRepository() {
        return new StockRepository();
    }


}
