package com.example.demo.repository;

import com.example.demo.model.Employees;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employees, Long> {

    Employees getByExternalId(Long externalId);

}
