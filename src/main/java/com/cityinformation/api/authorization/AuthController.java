package com.cityinformation.api.authorization;

import com.cityinformation.api.authorization.dto.LoginRequest;
import com.cityinformation.api.authorization.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return new LoginResponse(authService.authenticateUser(loginRequest.username(), loginRequest.password()));
    }
}
