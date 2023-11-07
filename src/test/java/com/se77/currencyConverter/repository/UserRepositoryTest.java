package com.se77.currencyConverter.repository;

import com.se77.currencyConverter.domain.jpa.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

/**
 * Created by superernie77 on 31.05.2017.
 */
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;


    @Test
    public void testCreateAndReadUser(){

        User user = new User();
        user.setLastName("Elias");
        user.setFirstName("Ernesto");
        user.setBirthday(new Date());
        user.setCityName("Freilassing");
        user.setCountryName("Germany");
        user.setEmail("se77@gmail.com");
        user.setPassword("testpassword");
        user.setStreetName("Laufenerstr. 5");
        user.setZipcode("83395");

        userRepo.save(user);

        User result = userRepo.findByEmail("se77@gmail.com");

        assertNotNull(result);
    }
}
