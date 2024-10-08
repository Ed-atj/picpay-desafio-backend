package com.picpay.desafio.domain.controller;

import com.picpay.desafio.domain.dto.UserDto;
import com.picpay.desafio.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    private ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        var createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }
}
