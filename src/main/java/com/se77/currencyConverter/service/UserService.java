package com.se77.currencyConverter.service;


import com.se77.currencyConverter.domain.jpa.User;

public interface UserService {

    /**
     * Returns a user by the email property
     * @param email email to search for
     * @return a user or null if none exists
     */
    public User findByEmail(String email);

    /**
     * Saves a new user to the DB.
     * @param user
     */
    public void saveUser(User user);
}