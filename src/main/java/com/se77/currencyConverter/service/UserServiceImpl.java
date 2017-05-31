package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.User;
import com.se77.currencyConverter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by superernie77 on 31.05.2017.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }


}
