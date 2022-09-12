package com.example.course2spring.controller;

import com.example.course2spring.model.Employee;
import com.example.course2spring.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam int salary,
        @RequestParam int departmentId
    ) {
        return employeeService.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/remove")
    public Employee remove(
        @RequestParam String firstName,
        @RequestParam String lastName
    ) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(
        @RequestParam String firstName,
        @RequestParam String lastName
    ) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/all")
    public Collection<Employee> find() {
        return employeeService.getAll();
    }
}
