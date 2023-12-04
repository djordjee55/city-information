package com.cityinformation.general.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedHandler;

import java.io.IOException;

@Slf4j
public class HttpRequestRejectedHandler implements RequestRejectedHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response,
                       org.springframework.security.web.firewall.RequestRejectedException requestRejectedException)
            throws IOException {

        log.error(requestRejectedException.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), requestRejectedException.getMessage());
    }
}
