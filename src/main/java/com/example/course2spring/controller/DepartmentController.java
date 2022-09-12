package com.example.course2spring.controller;

import com.example.course2spring.model.Department;
import com.example.course2spring.model.Employee;
import com.example.course2spring.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping( "/max-salary")
    public Employee findMaxSalaryByDepartment(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMaximumSalaryByDepartment(departmentId);
    }

    @GetMapping( "/min-salary")
    public Employee findMinSalaryByDepartment(@RequestParam int departmentId) {
        return departmentService.getEmployeeWithMinimumSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "/all", params = {"departmentId"})
    public List<Employee> findAllEmployeesByDepartment(@RequestParam(required = false) int departmentId) {
        return departmentService.findEmployeesByDepartmentNum(departmentId);
    }

    @GetMapping("/all")
    public Map<Department, List<Employee>> getEmployeesGroupByDepartment() {
        return departmentService.getPreparedByDepartment();
    }
}
