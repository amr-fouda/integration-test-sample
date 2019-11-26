package com.example.demo.services;


import com.example.demo.models.UserDto;

import java.util.Optional;

public interface UserService {
    void createUser(UserDto userDto);
    Optional<UserDto> findUserByUid(Long uid);
    Optional<UserDto> findUserByEmail(String Email);
}
