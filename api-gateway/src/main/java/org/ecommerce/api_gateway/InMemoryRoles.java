package org.ecommerce.api_gateway;

import org.springframework.stereotype.Component;

import java.util.List;


// Database integration
@Component
public class InMemoryRoles {
        private List<String> roles;

        public InMemoryRoles(List<String> roles) {
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

}
