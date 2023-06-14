package com.example.demo.repository;

import com.example.demo.model.SystemUser;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface SystemUserRepository extends CrudRepository<SystemUser, Long> {

    SystemUser findByUsername(String username);

    List<SystemUser> getAllBy();
}
