package com.example.demo.unit;

import com.example.demo.entities.UserEntity;
import com.example.demo.models.UserDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserValidationService;
import com.example.demo.services.impl.UserServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@DataJpaTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest //Unit tests should not include spring Ioc as it makes tests takes more time
public class UserServiceWithSpringIocNotRecommended {
    @Autowired
    private UserValidationService userValidationService;
    @MockBean // You can also use @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @Before
    public void init() {
        userService = new UserServiceImpl(userRepository, userValidationService);
    }

    @Test(expected = RuntimeException.class)//Exception example//should be special exception
    public void findUserTestWithInvalidUserId()
    {
        //when(userRepository.getOne(1000L)).thenReturn(null);// You can also use Mockito libary
        given(userRepository.findById(1000L)).willReturn(null);

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
