package com.picpay.desafio.domain.config;

import com.picpay.desafio.domain.entity.UserType;
import com.picpay.desafio.domain.repositories.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class RunnerConfig implements CommandLineRunner {

    private final UserTypeRepository userTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(UserType.Enum.values())
            .forEach(userType -> userTypeRepository.save(userType.get()));
    }
}
