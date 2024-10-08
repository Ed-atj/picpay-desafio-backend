package com.picpay.desafio.domain.mapper;

import com.picpay.desafio.domain.entity.User;
import com.picpay.desafio.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperHelper {

    private final UserRepository userRepository;

    @Named("idToUser")
    public User map(Long userId) {
        return userId != null ? userRepository.findById(userId).orElse(null) : null;
    }

    // Mapping User to Long
    @Named("userToId")
    public Long mapUserToId(User user) {
        return user != null ? user.getId() : null;
    }
}

