package com.example.course2spring.service;

import com.example.course2spring.exception.EmployeeAlreadyAddedException;
import com.example.course2spring.exception.EmployeeNotFoundException;
import com.example.course2spring.factory.EmployeeFactory;
import com.example.course2spring.model.Department;
import com.example.course2spring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final EmployeeFactory employeeFactory = new EmployeeFactory();
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee add(
        String firstName,
        String lastName,
        int salary,
        int department
    ) {
        Employee employee = employeeFactory.createItem(
            firstName,
            lastName,
            salary,
            new Department(department)
        );
        String key = generateKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException(firstName, lastName);
        }

        employees.put(key, employee);

        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        String key = generateKey(employee.getFirstName(), employee.getLastName());
        return employees.remove(key);
    }

    public Employee find(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException(firstName, lastName);
        }

        return employees.get(key);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }

    private String generateKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
