package com.example.demo.services.impl;

import com.example.demo.services.UserValidationService;
import com.example.demo.models.UserDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImpl implements UserValidationService {

    @Override
    public boolean isValidUser(UserDto userDto) {
        return userDto != null && userDto.getEmail() != null ;
    }
}
