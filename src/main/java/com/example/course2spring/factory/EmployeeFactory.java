package com.example.course2spring.factory;

import com.example.course2spring.model.Department;
import com.example.course2spring.model.Employee;

public class EmployeeFactory {
    public Employee createItem(String fullName, String lastName, int salary, Department department) {
        return new Employee(fullName, lastName, salary, department);
    }
}
