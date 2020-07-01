package com.junit_test.tests.service;

import com.junit_test.tests.model.Employee;
import com.junit_test.tests.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    Employee employee1;
    Employee employee2;
    List<Employee> employeeList = new ArrayList<>();
    List<String> firstNameList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        employee1 = new Employee("Ben", "Red", "mailben@mail");
        employee2 = new Employee("Stan", "Black", "stanmail@mail");
        employeeList.add(employee1);
        employeeList.add(employee2);
        firstNameList.add(employee1.getFirstName());
        firstNameList.add(employee2.getFirstName());
    }

    @AfterEach
    void tearDown() {
        employeeList.removeAll(employeeList);
        firstNameList.removeAll(firstNameList);
    }

    @Test
    void findAll() {
        when(employeeService.findAll()).thenReturn(employeeList);
        assertEquals(employeeService.findAll(), employeeList);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(employeeService.findAll(), employeeList);
    }

    @Test
    void selectAllFirstName() {
        when(employeeRepository.selectAllFirstName()).thenReturn(firstNameList);
        assertEquals(employeeService.selectAllFirstName(), firstNameList);
    }
    @Test
    void selectAllByLastNameSQL() {
        employeeList.remove(employee2);
        when(employeeRepository.selectAllByLastNameSQL("Red")).thenReturn(employeeList);
        assertEquals(employeeService.selectAllByLastNameSQL("Red"), employeeList);
    }
}