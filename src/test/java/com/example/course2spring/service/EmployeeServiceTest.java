package com.example.course2spring.service;

import com.example.course2spring.exception.EmployeeAlreadyAddedException;
import com.example.course2spring.exception.EmployeeNotFoundException;
import com.example.course2spring.model.Department;
import com.example.course2spring.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void addTest() {
        Employee employee = new Employee(
            "FirstName",
            "LastName",
            100,
            new Department(1)
        );

        employeeService.add(
            "FirstName",
            "LastName",
            100,
            1
        );

        List<Employee> employees = employeeService.getAll();
        assertTrue(employees.contains(employee));
        assertEquals(1, employees.size());
    }

    @Test
    void addDuplicateTest() {
        employeeService.add(
            "FirstName",
            "LastName",
            100,
            1
        );

        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(
            "FirstName",
            "LastName",
            100,
            1
        ));
    }

    @Test
    void removeTest() {
        Employee employee = new Employee(
            "FirstName",
            "LastName",
            100,
            new Department(1)
        );

        employeeService.add(
            "FirstName",
            "LastName",
            100,
            1
        );

        Employee removedEmployee = employeeService.remove("FirstName", "LastName");
        assertEquals(employee, removedEmployee);
        assertEquals(0, employeeService.getAll().size());
    }

    @Test
    void removeNotAddedEmployeeTest() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove("FirstName", "LastName"));
    }

    @Test
    void findTest() {
        Employee employee = new Employee(
            "FirstName",
            "LastName",
            100,
            new Department(1)
        );

        employeeService.add(
            "FirstName",
            "LastName",
            100,
            1
        );

        Employee findEmployee = employeeService.find("FirstName", "LastName");
        assertEquals(employee, findEmployee);
    }

    @Test
    void findNotAddedEmployeeTest() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("FirstName", "LastName"));
    }

    @Test
    void getAllTest() {
        List<Employee> employees = List.of(
            new Employee(
                "FirstName1",
                "LastName",
                100,
                new Department(1)
            ),
            new Employee(
                "FirstName2",
                "LastName",
                100,
                new Department(1)
            ),
            new Employee(
                "FirstName3",
                "LastName",
                100,
                new Department(1)
            )
        );

        for (Employee employee : employees) {
            employeeService.add(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment().getDepartmentNum()
            );
        }

        assertEquals(employees, employeeService.getAll());
    }

    @Test
    void getAllEmptyTest() {
        assertEquals(List.of(), employeeService.getAll());
    }
}