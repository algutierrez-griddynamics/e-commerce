package org.ecommerce.config;

import org.ecommerce.controllers.AdminController;
import org.ecommerce.controllers.CustomerController;
import org.ecommerce.controllers.ManagerController;
import org.ecommerce.controllers.OrderController;
import org.ecommerce.models.Manager;
import org.ecommerce.services.OrderService;
import org.ecommerce.services.PasswordService;
import org.ecommerce.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(ApplicationServicesConfig.class)
@Configuration
public class ApplicationControllersConfig {

    @Bean
    AdminController adminController() {
        return new AdminController();
    }

    @Bean
    CustomerController customerController(PasswordService passwordService) {
        return new CustomerController(passwordService);
    }

    @Bean
    ManagerController managerController(UserService<Manager> managerService, PasswordService passwordService) {
        return new ManagerController(managerService, passwordService);
    }

    @Bean
    OrderController orderController(OrderService orderService) {
        return new OrderController(orderService);
    }

}
