package com.example.demo.dao;


import com.example.demo.model.SystemUser;

import java.util.List;

public interface SystemUserDao {

    List<SystemUser> getAll();

    SystemUser getById(Long id);

    long insert(SystemUser systemUser);

}
