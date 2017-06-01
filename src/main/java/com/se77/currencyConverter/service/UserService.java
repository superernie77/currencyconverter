package com.se77.currencyConverter.service;


import com.se77.currencyConverter.domain.User;

public interface UserService {

    public User findByEmail(String email);

    public void saveUser(User user);
}