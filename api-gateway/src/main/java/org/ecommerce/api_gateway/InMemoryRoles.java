package org.ecommerce.api_gateway;

import org.springframework.stereotype.Component;

import java.util.List;


// Such roles might be stored in a role matrix in the db
@Component
public class InMemoryRoles {
        private List<String> roles;

        public InMemoryRoles(List<String> roles) {
            this.roles = roles;
        }

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

}
