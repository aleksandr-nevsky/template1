package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class EmployeeFromStub {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
