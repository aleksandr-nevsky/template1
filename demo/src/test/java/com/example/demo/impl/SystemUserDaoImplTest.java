package com.example.demo.impl;

import com.example.demo.dao.impl.SystemUserDaoImpl;
import com.example.demo.model.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class SystemUserDaoImplTest {

    @Autowired
    private SystemUserDaoImpl systemUserDao;

    @Test
    void getAll() {
        List<SystemUser> all = systemUserDao.getAll();
        assertEquals(2, all.size());
    }

    @Test
    @Transactional
    void insertSystemUser() {
        SystemUser systemUser = SystemUser.builder()
                .username("username")
                .password("password")
                .roles(Arrays.asList("roles1", "roles2"))
                .build();

        long insertedObjectId = systemUserDao.insert(systemUser);

        SystemUser userFromDb = systemUserDao.getById(insertedObjectId);

        assertEquals("username", userFromDb.getUsername());
    }

}