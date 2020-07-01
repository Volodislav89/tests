package com.junit_test.tests.repository;

import com.junit_test.tests.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT first_name FROM employees", nativeQuery = true)
    List<String> selectAllFirstName();
    @Query(value = "SELECT * FROM employees WHERE last_name = ?1", nativeQuery = true)
    List<Employee> selectAllByLastNameSQL(String lastName);
    @Query(value = "SELECT * FROM employees WHERE first_name LIKE ?1", nativeQuery = true)
    List<Employee> selectAllLikeSQL(String like);

    @Query(value = "SELECT e.firstName FROM Employee e")
    List<String> selectAllFirstNameJPQL();
    @Query(value = "FROM Employee e WHERE e.firstName LIKE ?1")
    List<Employee> selectAllLikeJPQL(String like);
    @Query(value = "FROM Employee e WHERE e.lastName = ?1")
    List<Employee> selectAllByLastNameJPQL(String lastName);

    List<Employee> findAllByFirstNameOrderByLastNameAsc(String firstName);
    List<Employee> findAllByFirstNameLike(String firstName);
    Employee findFirstByFirstName(String username);
}
