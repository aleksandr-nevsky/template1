package com.example.demo.repository;

import com.example.demo.model.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
class SystemUserRepositoryTest {
    @Autowired
    SystemUserRepository systemUserRepository;

    @Test
    @Disabled
    @Transactional
    void saveSystemUser() {
        String password = new BCryptPasswordEncoder().encode("password");
        List<String> roles = Arrays.asList("USER", "ADMIN");
        SystemUser systemUser = SystemUser.builder()
                .username("user2")
                .password(password)
                .roles(roles)
                .build();

        systemUserRepository.save(systemUser);

    }

    @Test
    @Disabled
    void encrypt() {
        String encode = new BCryptPasswordEncoder().encode("password");
        log.info(encode);
    }

}