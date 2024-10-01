package com.picpay.desafio.domain.services;

import com.picpay.desafio.domain.dto.UserDto;
import com.picpay.desafio.domain.exception.UserAlreadyExistsException;
import com.picpay.desafio.domain.mapper.UserMapper;
import com.picpay.desafio.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserDto createUser (UserDto userDto){
        var existingUser = repository.findByEmail(userDto.email());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("Email already exists.");
        }
        var newUser = repository.save(mapper.toUser(userDto));
        return mapper.toDto(newUser);
    }
}
