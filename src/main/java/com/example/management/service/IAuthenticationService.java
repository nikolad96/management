package com.example.management.service;

import com.example.management.model.User;

public interface IAuthenticationService {

    public User registerUser(User user);

    public String login(String username, String password);
}
