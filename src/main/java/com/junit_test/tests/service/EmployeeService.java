package com.junit_test.tests.service;

import com.junit_test.tests.model.Employee;
import com.junit_test.tests.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    //SQL
    public List<String> selectAllFirstName() {
        return employeeRepository.selectAllFirstName();
    }
    public List<Employee> selectAllByLastNameSQL(String lastName) {
        return employeeRepository.selectAllByLastNameSQL(lastName);
    }
    public List<Employee> selectAllLikeSQL(String like) {
        return employeeRepository.selectAllLikeSQL(like);
    }

    //JPQL
    public List<String> selectAllFirstNameJPQL() {
        return employeeRepository.selectAllFirstNameJPQL();
    }
    public List<Employee> selectAllLikeJPQL(String like) {
        return employeeRepository.selectAllLikeJPQL(like);
    }
    public List<Employee> selectAllByLastNameJPQL(String lastName) {
        return employeeRepository.selectAllByLastNameJPQL(lastName);
    }

    //KeyWords
    public List<Employee> findAllByFirstNameOrderByLastNameAsc(String firstName) {
        return employeeRepository.findAllByFirstNameOrderByLastNameAsc(firstName);
    }
    public List<Employee> findAllByFirstNameLike(String firstName) {
        return employeeRepository.findAllByFirstNameLike(firstName);
    }
    public Employee findFirstByFirstName(String username) {
        return employeeRepository.findFirstByFirstName(username);
    }
}
