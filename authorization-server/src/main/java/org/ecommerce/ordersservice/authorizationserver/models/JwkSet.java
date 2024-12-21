package org.ecommerce.ordersservice.authorizationserver.models;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JwkSet {

    private static Set<String> jwkList;
    private static int kid;


    static {
        jwkList = new HashSet<>();
        kid = 0;
    }

    public void addJwk(String jwk) {
        jwkList.add(jwk);
    }

    public Set<String> getJwkSet() {
        return new HashSet<>(jwkList);
    }

    public String getKidString() {
        return String.valueOf(kid++);
    }
}
