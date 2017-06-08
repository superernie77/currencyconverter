package com.se77.currencyConverter.repository;

import static org.junit.Assert.*;

import com.se77.currencyConverter.domain.jpa.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by superernie77 on 31.05.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepo;


    @Test
    public void testCreateAndReadUser(){

        User user = new User();
        user.setLastName("Elias");
        user.setFirstname("Ernesto");
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
