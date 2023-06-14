package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Builder
public class SystemUser {
    @Id
    private Long id;
    private String username;
    private String password;
    private List<String> roles;

//    public List<String> getRoles() {
//        String[] split = roles.split(",");
//        return Arrays.asList(split);
//    }
}
