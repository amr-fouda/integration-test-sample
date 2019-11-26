package com.example.demo.integration;

import com.example.demo.DemoApplication;
import com.example.demo.models.UserDto;
import com.example.demo.services.UserService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@TestPropertySource(
        locations = "classpath:application.properties")
public class UserServiceIT {

    @Autowired
    private UserService userService;

    final String SAMPLE_EMAIL="a@a.com";

    @Test
    public void createUserTest()
    {
        userService.createUser(UserDto.builder().email(SAMPLE_EMAIL).build());

        Optional<UserDto> userDto= userService.findUserByEmail(SAMPLE_EMAIL);

        Assert.assertNotNull(userDto);
        Assert.assertTrue(userDto.get().getEmail().equalsIgnoreCase(SAMPLE_EMAIL));
    }

    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:DB/SAMPLE_USERS.sql")
    @Test
    public void findUserByEmailTest()
    {
        Optional<UserDto> userDto= userService.findUserByEmail("b@b.com");
        Assert.assertNotNull(userDto);
        Assert.assertNotNull(userDto.get());
    }

}
