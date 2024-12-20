package org.ecommerce.api_gateway;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.SignedJWT;
import org.ecommerce.api_gateway.feign.AuthenticationServiceInterface;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public class RoleBasedFilter extends AbstractGatewayFilterFactory<InMemoryRoles> {


    private final AuthenticationServiceInterface authenticationServiceInterface;

    public RoleBasedFilter(@Lazy AuthenticationServiceInterface authenticationServiceInterface) {
        this.authenticationServiceInterface = authenticationServiceInterface;
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

            } catch (JOSEException | ParseException e) {
                throw new RuntimeException(e);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isSignatureCorrect(String token) throws JOSEException, ParseException {
        RSAKey rsaPublicJWK = new RSAKeyGenerator(2048)
                .keyID("1")
                .generate()
                .toPublicJWK();

        JWKSet jwkSet = new JWKSet((List<JWK>) authenticationServiceInterface.getKeys());

        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);

        var res = signedJWT.verify(verifier);

        return res;
    }

//    private boolean isSignatureCorrect(String token, RSAKey rsaPublicJWK) throws JOSEException, ParseException {
//        SignedJWT signedJWT = SignedJWT.parse(token);
//
//        JWSVerifier verifier = new RSASSAVerifier(rsaPublicJWK);
//
//        return signedJWT.verify(verifier);
//    }


    private boolean hasRequiredRole(SignedJWT signedJWT, List<String> roles) throws ParseException {
        String role = signedJWT.getJWTClaimsSet().getStringClaim("role");
        return roles.contains(role);
    }

    private String trimToken(String token) {
        return token.substring(7);
    }


}
