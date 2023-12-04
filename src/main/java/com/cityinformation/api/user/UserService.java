package com.cityinformation.api.user;

import com.cityinformation.api.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userEntity ->
                new User(userEntity.getId(), userEntity.getUsername(), userEntity.getEncodedPassword()));
    }
}
