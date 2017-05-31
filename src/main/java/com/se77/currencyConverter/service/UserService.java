package com.se77.currencyConverter.service;


import com.se77.currencyConverter.domain.User;

public interface UserService {

    public User findUserByLastName(String name);

    public void saveUser(User user);
}