package com.example.demo.services;


import com.example.demo.models.UserDto;

public interface UserValidationService {
    boolean isValidUser(UserDto userDto);
}
