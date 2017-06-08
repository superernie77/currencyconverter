package com.se77.currencyConverter.service;

import com.se77.currencyConverter.domain.jpa.Role;
import com.se77.currencyConverter.domain.jpa.User;
import com.se77.currencyConverter.repository.RoleRepository;
import com.se77.currencyConverter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by superernie77 on 31.05.2017.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {

        if ( userRepo.findByEmail(user.getEmail()) == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Role userRole = roleRepository.findByRole("USER");
            if (userRole == null){
                userRole = new Role();
                userRole.setRole("USER");
            }
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepo.save(user);
        } else {
            //TODO error message
        }
    }
}
