package com.example.demo.unit;

import com.example.demo.services.UserValidationService;
import com.example.demo.entities.UserEntity;
import com.example.demo.models.UserDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.impl.UserServiceImpl;
import com.example.demo.services.impl.UserValidationServiceImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestWithMockitoRunnerTest {
    private UserService userService;
    private UserValidationService userValidationService;
    private UserRepository userRepository;

    @Before
    public void init() {
        userValidationService = new UserValidationServiceImpl();
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, userValidationService);
    }

    @Test(expected = RuntimeException.class)//Exception example//should be specical exception
    public void findUserTestWithInvalidUserId()
    {
        Mockito.when(userRepository.findById(1000L)).thenReturn(null);

        userService.findUserByUid(1000L);
    }

    @Test
    public void findUserTestWithValidUserId()
    {
        Mockito.when(userRepository.findById(1000L))
                .thenReturn(Optional.of(UserEntity.builder().uid(1000L).email("a@a.com").build()));

        Optional<UserDto> userDto= userService.findUserByUid(1000L);

        Mockito.verify(userRepository).findById(1000L);//Verification example
        Assert.assertEquals(userDto.get().getEmail(),"a@a.com");//Asseration example
        //More asseration
    }
}
