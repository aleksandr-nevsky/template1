package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class Employees {
    @Id
    private Long id;
    private String externalId;
    private String firstName;
    private String lastName;
    private String email;

}
