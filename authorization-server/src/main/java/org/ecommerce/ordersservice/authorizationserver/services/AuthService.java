package org.ecommerce.ordersservice.authorizationserver.services;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.ecommerce.ordersservice.authorizationserver.models.AuthRequest;
import org.ecommerce.ordersservice.authorizationserver.models.JwkSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Service
public class AuthService {

    private final JwkSet jwkSet;

    public AuthService(JwkSet jwkSet) {
        this.jwkSet = jwkSet;
    }


    public ResponseEntity<String> validateCredentials(AuthRequest authRequest) {
        try {
            // I should check in here with the database, using the Encryption bean
            if ("manager".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
                return ResponseEntity.ok(generateToken("MANAGER"));
            } else if ("cashier".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
                return ResponseEntity.ok(generateToken("CASHIER"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            System.out.println("Error generating token " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating token");
        }
    }

    private String generateToken(String role) throws JOSEException, ParseException {
        Date issueTime = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date expirationTime = Date.from(LocalDateTime.now().plusHours(1L).atZone(ZoneId.systemDefault()).toInstant());

        RSAKey rsaJWK = new RSAKeyGenerator(2048)
                .keyID(jwkSet.getKidString())
                .generate();

        RSAPrivateKey rsaPrivateKey = rsaJWK.toRSAPrivateKey();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("user")
                .claim("role", role)
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();

        JWSSigner signer = new RSASSASigner(rsaPrivateKey);

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJWK.getKeyID()).build(),
                claimsSet);

        signedJWT.sign(signer);

        jwkSet.addJwk(rsaJWK.toPublicJWK().toJSONString());

        return signedJWT.serialize();
    }

    public ResponseEntity<Set<String>> getKeys() {
        return ResponseEntity.ok(jwkSet.getJwkSet());
    }
}
