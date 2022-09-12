package com.example.course2spring.service;

import com.example.course2spring.exception.NotFoundEmployeesByDepartment;
import com.example.course2spring.model.Department;
import com.example.course2spring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaximumSalaryByDepartment(int departmentId) {
        return employeeService
            .getAll()
            .stream()
            .filter(e -> e.getDepartment().getDepartmentNum() == departmentId)
            .max(Comparator.comparing(Employee::getSalary))
            .orElseThrow(() -> new NotFoundEmployeesByDepartment(departmentId));
    }

    public Employee getEmployeeWithMinimumSalaryByDepartment(int departmentId) {
        return employeeService
            .getAll()
            .stream()
            .filter(e -> e.getDepartment().getDepartmentNum() == departmentId)
            .min(Comparator.comparing(Employee::getSalary))
            .orElseThrow(() -> new NotFoundEmployeesByDepartment(departmentId))
        ;
    }

    public List<Employee> findEmployeesByDepartmentNum(int departmentId) {
        return employeeService
            .getAll()
            .stream()
            .filter(e -> e.getDepartment().getDepartmentNum() == departmentId)
            .collect(Collectors.toList())
            ;
    }

    public Map<Department, List<Employee>> getPreparedByDepartment() {
        return employeeService
            .getAll()
            .stream()
            .collect(Collectors.groupingBy(Employee::getDepartment))
        ;
    }
}
