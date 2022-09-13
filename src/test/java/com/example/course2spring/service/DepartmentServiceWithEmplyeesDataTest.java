package com.example.course2spring.service;

import com.example.course2spring.exception.NotFoundEmployeesByDepartment;
import com.example.course2spring.model.Department;
import com.example.course2spring.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceWithEmplyeesDataTest {
    private static final int TEST_SUCCESS_DEPARTMENT = 1;

    private static final int TEST_NOT_FOUND_DEPARTMENT = Integer.MAX_VALUE;

    private final EmployeeService employeeService = mock(EmployeeService.class);

    private DepartmentService departmentService;

    @Test
    void getEmployeeWithMaximumSalaryByDepartmentTest() {
        Employee employee = departmentService
            .getEmployeeWithMaximumSalaryByDepartment(TEST_SUCCESS_DEPARTMENT);

        assertEquals(employee, new Employee(
            "FirstName12",
            "LastName",
            3000,
            new Department(1)
        ));
    }

    @Test
    void getEmployeeWithMaximumSalaryByDepartmentWithExceptionTest() {
        assertThrows(
            NotFoundEmployeesByDepartment.class,
            () -> departmentService
                .getEmployeeWithMaximumSalaryByDepartment(TEST_NOT_FOUND_DEPARTMENT)
        );
    }

    @Test
    void getEmployeeWithMinimumSalaryByDepartmentTest() {
        Employee employee = departmentService
            .getEmployeeWithMinimumSalaryByDepartment(TEST_SUCCESS_DEPARTMENT);

        assertEquals(employee, new Employee(
            "FirstName1",
            "LastName",
            100,
            new Department(1)
        ));
    }

    @Test
    void getEmployeeWithMinimumSalaryByDepartmentWithExceptionTest() {
        assertThrows(
            NotFoundEmployeesByDepartment.class,
            () -> departmentService
                .getEmployeeWithMinimumSalaryByDepartment(TEST_NOT_FOUND_DEPARTMENT)
        );
    }

    @Test
    void findEmployeesByDepartmentNumTest() {
        assertEquals(
            departmentService
                .findEmployeesByDepartmentNum(TEST_SUCCESS_DEPARTMENT),
            List.of(
                new Employee(
                    "FirstName1",
                    "LastName",
                    100,
                    new Department(1)
                ),
                new Employee(
                    "FirstName12",
                    "LastName",
                    3000,
                    new Department(1)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("getEmployeesMap")
    void getPreparedByDepartment(Map<Department, List<Employee>> expected) {
        assertEquals(
            departmentService
                .getPreparedByDepartment(),
            expected
        );
    }

    @BeforeEach
    void setUp() {
        departmentService = new DepartmentService(employeeService);
        when(employeeService.getAll()).thenReturn(List.of(
            new Employee(
                "FirstName1",
                "LastName",
                100,
                new Department(1)
            ),
            new Employee(
                "FirstName12",
                "LastName",
                3000,
                new Department(1)
            ),
            new Employee(
                "FirstName2",
                "LastName",
                100,
                new Department(2)
            ),
            new Employee(
                "FirstName3",
                "LastName",
                100,
                new Department(3)
            ),
            new Employee(
                "FirstName4",
                "LastName",
                100,
                new Department(4)
            ),
            new Employee(
                "FirstName5",
                "LastName",
                100,
                new Department(5)
            ),
            new Employee(
                "FirstName6",
                "LastName",
                100,
                new Department(6)
            )
        ));
    }

    public static Stream<Arguments> getEmployeesMap() {
        Map<Department, List<Employee>> employeeMappingToDepartment = new HashMap<>();
        employeeMappingToDepartment.put(
            new Department(1),
            List.of(
                new Employee(
                    "FirstName1",
                    "LastName",
                    100,
                    new Department(1)
                ),
                new Employee(
                    "FirstName12",
                    "LastName",
                    3000,
                    new Department(1)
                )
            )
        );

        employeeMappingToDepartment.put(
            new Department(2),
            List.of(
                new Employee(
                    "FirstName2",
                    "LastName",
                    100,
                    new Department(2)
                )
            )
        );

        employeeMappingToDepartment.put(
            new Department(3),
            List.of(
                new Employee(
                    "FirstName3",
                    "LastName",
                    100,
                    new Department(3)
                )
            )
        );

        employeeMappingToDepartment.put(
            new Department(4),
            List.of(
                new Employee(
                    "FirstName4",
                    "LastName",
                    100,
                    new Department(4)
                )
            )
        );

        employeeMappingToDepartment.put(
            new Department(5),
            List.of(
                new Employee(
                    "FirstName5",
                    "LastName",
                    100,
                    new Department(5)
                )
            )
        );

        employeeMappingToDepartment.put(
            new Department(6),
            List.of(
                new Employee(
                    "FirstName6",
                    "LastName",
                    100,
                    new Department(6)
                )
            )
        );
        return Stream.of(
            Arguments.of(employeeMappingToDepartment)
        );
    }
}