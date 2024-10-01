package com.picpay.desafio.domain.mapper;

import com.picpay.desafio.domain.dto.UserDto;
import com.picpay.desafio.domain.entity.User;
import com.picpay.desafio.domain.entity.UserType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toUser(UserDto userDto);

    default UserType toUserType(String userType) {
        return UserType.fromString(userType);
    }
}
