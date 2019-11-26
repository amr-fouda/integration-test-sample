package com.example.demo.unit;

import com.example.demo.entities.UserEntity;
import com.example.demo.services.UserValidationService;
import com.example.demo.services.impl.UserServiceImpl;
import com.example.demo.models.UserDto;
import com.example.demo.repositories.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestWithMockitoRunnerAndMockitoAnnotationTest {
    @Mock
    private UserValidationService userValidationService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

/* No need to init()
    @Before
    public void init() {
        userValidationService = new UserValidationServiceImpl();
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, userValidationService);
    }
*/
    @Test(expected = RuntimeException.class)//Exception example//should be special exception
    public void findUserTestWithInvalidUserId()
    {
        when(userRepository.findById(1000L)).thenReturn(null);

        userService.findUserByUid(1000L);
    }

    @Test
    public void findUserTestWithValidUserId()
    {
        when(userRepository.findById(1000L))
                .thenReturn(Optional.of(UserEntity.builder().uid(1000L).email("a@a.com").build()));

        Optional<UserDto> userDto= userService.findUserByUid(1000L);

        verify(userRepository).findById(1000L);//Verification example
        assertEquals(userDto.get().getEmail(),"a@a.com");//Asseration example
        //More asseration
    }
}
