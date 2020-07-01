package com.junit_test.tests.controller;

import com.junit_test.tests.model.Employee;
import com.junit_test.tests.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
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
    void selectAllFirstName() throws Exception {
        when(employeeService.selectAllFirstName()).thenReturn(firstNameList);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/emp")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]", is(firstNameList.get(0))))
                .andExpect(jsonPath("$[1]", is(firstNameList.get(1))));
    }
}