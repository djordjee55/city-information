package com.cityinformation.general.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtCreator {

    private static final String JWT_ISSUER = "https://www.city-information.com";

    private final JwtEncoder jwtEncoder;

    public String createJwtToken(String subject) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer(JWT_ISSUER)
                .subject(subject)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();
    }
}
