package com.cityinformation.api.authorization;

import com.cityinformation.api.user.UserService;
import com.cityinformation.api.user.model.User;
import com.cityinformation.general.exceptions.InvalidCredentialException;
import com.cityinformation.general.security.jwt.JwtCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtCreator jwtCreator;
    private final UserService userService;

    public String authenticateUser(String username, String password) {

        User user = userService.getUserByUsername(username)
                .orElseThrow(InvalidCredentialException::new);

        if (passwordEncoder.matches(password, user.encodedPassword())) {
            return jwtCreator.createJwtToken(user.id().toString());
        }

        throw new InvalidCredentialException();
    }
}
