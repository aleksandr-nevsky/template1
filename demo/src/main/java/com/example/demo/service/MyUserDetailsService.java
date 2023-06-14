package com.example.demo.service;

import com.example.demo.model.MyUserPrincipal;
import com.example.demo.model.SystemUser;
import com.example.demo.repository.SystemUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;

    public MyUserDetailsService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        SystemUser systemUser = systemUserRepository.findByUsername(username);
        if (systemUser == null) {
            throw new UsernameNotFoundException(username);
        }

        List<String> roles = systemUser.getRoles();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return new MyUserPrincipal(systemUser, grantedAuthorities);
    }
}