package org.ecommerce.api_gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import org.ecommerce.api_gateway.models.Jwks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleBasedFilter extends AbstractGatewayFilterFactory<InMemoryRoles> {

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public static final ObjectMapper MAPPER = new ObjectMapper();


    public RoleBasedFilter() {
    }

    @Override
    public GatewayFilter apply(InMemoryRoles inMemoryRoles) {
        return (exchange, chain) -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            try {
                if (authHeader == null || !authHeader.startsWith("Bearer ") || !isSignatureCorrect(trimToken(authHeader))) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                if (!hasRequiredRole(SignedJWT.parse(trimToken(authHeader)), inMemoryRoles.getRoles())){
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }

            } catch (JOSEException | ParseException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            return chain.filter(exchange);
        };
    }


    private boolean isSignatureCorrect(String token) throws JOSEException, ParseException, JsonProcessingException {
        final String authServiceUrlJwks = authServiceUrl + "/auth/keys";

        SignedJWT signedJWTOG = SignedJWT.parse(token);

        String kid = signedJWTOG.getHeader().getKeyID();

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(authServiceUrlJwks, String.class);

        List<String> jsonStrings = MAPPER.readValue(jsonResponse, new TypeReference<List<String>>() {});

        Jwks currentJwk = checkKid(kid, jsonStrings);

        RSAKey rsaPublicJWK = RSAKey.parse(MAPPER.writeValueAsString(currentJwk));

        JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);

        var x = signedJWTOG.verify(verifier);

        return x;
    }


    private Jwks checkKid(String kid, List<String> jsonStrings) {
        return getJwksListFromStringList(jsonStrings)
                .stream().filter(jwks -> jwks.getKid().equals(kid))
                .findFirst().orElseThrow(() -> new RuntimeException("kid not found"));
    }

    private List<Jwks> getJwksListFromStringList(List<String> jsonStrings) {
        return jsonStrings.stream().map(jsonString -> {
                    try {
                        return MAPPER.readValue(jsonString, Jwks.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
    }

    private boolean hasRequiredRole(SignedJWT signedJWT, List<String> roles) throws ParseException {
        String role = signedJWT.getJWTClaimsSet().getStringClaim("role");
        return roles.contains(role);
    }

    private String trimToken(String token) {
        return token.substring(7);
    }


}
