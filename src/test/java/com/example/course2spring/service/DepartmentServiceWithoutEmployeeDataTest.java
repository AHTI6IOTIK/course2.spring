package com.example.course2spring.service;

import com.example.course2spring.exception.NotFoundEmployeesByDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceWithoutEmployeeDataTest {
    private final EmployeeService employeeService = mock(EmployeeService.class);

    private DepartmentService departmentService;

    @Test
    void getEmployeeWithMaximumSalaryByDepartmentWithExceptionTest() {
        assertThrows(
            NotFoundEmployeesByDepartment.class,
            () -> departmentService
                .getEmployeeWithMaximumSalaryByDepartment(1)
        );
    }

    @Test
    void getEmployeeWithMinimumSalaryByDepartmentWithExceptionTest() {
        assertThrows(
            NotFoundEmployeesByDepartment.class,
            () -> departmentService
                .getEmployeeWithMinimumSalaryByDepartment(2)
        );
    }

    @Test
    void findEmployeesByDepartmentNumTest() {
        assertEquals(
            departmentService
                .findEmployeesByDepartmentNum(4),
            List.of()
        );
    }

    @Test
    void getPreparedByDepartment() {
        assertEquals(
            departmentService.getPreparedByDepartment(),
            new HashMap<>()
        );
    }

    @BeforeEach
    void setUp() {
        departmentService = new DepartmentService(employeeService);
        when(employeeService.getAll()).thenReturn(List.of());
    }
}