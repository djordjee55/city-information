package com.cityinformation.general.security;

import com.cityinformation.general.security.token.AuthenticationToken;
import com.cityinformation.general.security.token.VerifiedToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ActiveTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        VerifiedToken verifiedToken = (VerifiedToken) SecurityContextHolder.getContext().getAuthentication();

        setAuthenticatedTokenToContext(verifiedToken);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication == null || !VerifiedToken.class.isAssignableFrom(authentication.getClass());
    }

    private void setAuthenticatedTokenToContext(VerifiedToken verifiedToken) {
        AuthenticationToken authenticationToken = new AuthenticationToken(verifiedToken.getUserId());
        authenticationToken.setDetails(verifiedToken.getDetails());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
