package com.cityinformation.general.security.jwt;

import com.cityinformation.general.security.token.VerifiedToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtToVerifiedTokenConverter implements Converter<Jwt, VerifiedToken> {

    @Override
    public VerifiedToken convert(Jwt jwt) {
        String userId = jwt.getSubject();

        return new VerifiedToken(userId);
    }
}
