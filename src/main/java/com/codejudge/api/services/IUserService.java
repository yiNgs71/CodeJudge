package com.codejudge.api.services;

import com.codejudge.api.models.User;

import java.util.Optional;

public interface IUserService {
    User createUser(User user);
    Optional<User> findUserByUsername(String username);
}
