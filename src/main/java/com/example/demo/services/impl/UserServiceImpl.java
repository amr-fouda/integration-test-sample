package com.example.demo.services.impl;

import com.example.demo.services.UserValidationService;
import com.example.demo.entities.UserEntity;
import com.example.demo.models.UserDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private UserValidationService userValidationService;

    @Override
    public void createUser(UserDto userDto) {
        if (userValidationService.isValidUser(userDto)) {
            //Transformation service
            //service class should not deal with entities . It should only deal with dto
            userRepository.save(UserEntity.builder().email(userDto.getEmail()).build());
        } else {
            //Don't throw generic exception
            //Throw special type of runtime exception to rollback the transaction in oop + include it in exception mapping
            throw new RuntimeException("Invalid user data exception");
        }

    }

    @Override
    public Optional<UserDto> findUserByUid(Long uid) {
        Optional<UserEntity> userEntity = userRepository.findById(uid);
        if (userEntity.isPresent())
            //Mapping should be done using third party lib or a second service class
            return Optional.of(UserDto.builder().email(userEntity.get().getEmail()).uid(userEntity.get().getUid()).build());
        else
            //Throw exception if we didn't find the entity -> If you are in OOP and want DB trx to rollback
            //Return Optional ref --> if you are at FP
            //Don't throw generic exception
            //Throw a special runtime exception to rollback the transaction in oop + include it in exception mapping
            return Optional.empty();

    }

    @Override
    public Optional<UserDto> findUserByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isPresent())
            //Mapping should be done using libary or a second service class
            return Optional.of(UserDto.builder().email(userEntity.get().getEmail()).uid(userEntity.get().getUid()).build());
        else
            //Throw exception if we didn't find the entity -> If you are in OOP and want DB trx to rollback
            //Return Optional ref --> if you are at FP
            //Don't throw generic exception
            //Throw specilized runtime exception to rollback the transaction in oop + include it in exception mapping
            return Optional.empty();
    }
}
