package org.ecommerce.ordersservice.authorizationserver.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class test {

    @GetMapping(path = "/test")
    public String getTest() {

        return "Testing controller";
    }
}
