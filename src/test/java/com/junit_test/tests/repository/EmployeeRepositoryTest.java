package com.junit_test.tests.repository;

import com.junit_test.tests.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired EmployeeRepository employeeRepository;

    Employee employee1;
    Employee employee2;
    List<String> firstNameList = new ArrayList<>();
    List<Employee> testEmployeeList = new ArrayList<>();
    List<Employee> employeeListTest = new ArrayList<>();

    @BeforeEach
    void setUp() {
        employee1 = new Employee("admin", "admin1", "admin1@gmail.com");
        employee2 = new Employee("admin", "admin", "admin@gmail.com");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        firstNameList.add(employee1.getFirstName());
        firstNameList.add(employee2.getFirstName());
        testEmployeeList.add(employee1);
        employeeListTest.add(employee1);
        employeeListTest.add(employee2);
    }

    @AfterEach
    void tearDown() {
        employee1 = null;
        employee2 = null;
        employeeRepository.deleteAll();
        firstNameList = null;
    }

    //KeyWords
    @Test
    void findByFirstName() {
        Employee employee = employeeRepository.findFirstByFirstName("admin");
        assertEquals(employee1.getFirstName(), employee.getFirstName());
        assertEquals(employee1.getLastName(), employee.getLastName());
    }
    @Test
    void findAllByFirstNameLike() {
        List<Employee> employeeList = employeeRepository.findAllByFirstNameLike("adm%");
        assertEquals(employeeListTest, employeeList);
    }
    @Test
    void findAllByFirstNameOrderByLastNameAsc() {
        //employeeListTest.sort(Comparator.comparing(Employee::getLastName).reversed()); DESC
        employeeListTest.sort(Comparator.comparing(Employee::getLastName));
        List<Employee> employeeList = employeeRepository.findAllByFirstNameOrderByLastNameAsc("admin");
        assertEquals(employeeListTest, employeeList);
    }

    //SQL
    @Test
    void selectAllFirstName() {
        List<String> stringList = employeeRepository.selectAllFirstName();
        assertEquals(stringList, firstNameList);
    }
    @Test
    void selectAllByLastNameSQL() {
        List<Employee> employeeList = employeeRepository.selectAllByLastNameSQL("admin1");
        assertEquals(testEmployeeList, employeeList);
    }
    @Test
    void selectAllLikeSQL() {
        List<Employee> employeeList = employeeRepository.selectAllLikeSQL("adm%");
        assertEquals(employeeListTest, employeeList);
    }

    //JPQL
    @Test
    void selectAllFirstNameJPQL() {
        List<String> stringList = employeeRepository.selectAllFirstNameJPQL();
        assertEquals(stringList, firstNameList);
    }
    @Test
    void selectAllLikeJPQL() {
        List<Employee> employeeList = employeeRepository.selectAllLikeJPQL("adm%");
        assertEquals(employeeListTest, employeeList);
    }
    @Test
    void selectAllByLastNameJPQL() {
        List<Employee> employeeList = employeeRepository.selectAllByLastNameJPQL("admin1");
        assertEquals(testEmployeeList, employeeList);
    }
}