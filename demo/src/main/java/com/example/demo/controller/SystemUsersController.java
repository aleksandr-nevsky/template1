package com.example.demo.controller;

import com.example.demo.dao.SystemUserDao;
import com.example.demo.model.SystemUser;
import com.example.demo.repository.SystemUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemUsersController {

    private final SystemUserRepository systemUserRepository;

    private final SystemUserDao systemUserDao;

    public SystemUsersController(SystemUserRepository systemUserRepository, SystemUserDao systemUserDao) {
        this.systemUserRepository = systemUserRepository;
        this.systemUserDao = systemUserDao;
    }

    @GetMapping("/admin/get-system-users")
    List<SystemUser> getUsers() {
        List<SystemUser> users = systemUserRepository.getAllBy();
        users.forEach(u -> u.setPassword(""));

        return users;
    }

    @GetMapping("/admin/get-system-users-from-dto")
    List<SystemUser> getUsersFromDto() {
        return systemUserDao.getAll();
    }

    @GetMapping("/admin/get-system-users-from-dto-by-id")
    SystemUser getUsersFromDtoById(@RequestParam Long id) {
        return systemUserDao.getById(id);
    }

    @GetMapping("/admin/insert-system-user")
    long insertUser(@RequestParam String username,
                    @RequestParam String password,
                    @RequestParam List<String> roles) {
        SystemUser systemUser = SystemUser.builder()
                .username(username)
                .password(password)
                .roles(roles)
                .build();

        return systemUserDao.insert(systemUser);
    }
}
