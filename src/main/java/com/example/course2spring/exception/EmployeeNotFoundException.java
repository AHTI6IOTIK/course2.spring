package com.example.course2spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String fullName, String lastName) {
        super(String.format(
            "Сотрудник #%s %s, не найден",
            fullName,
            lastName
        ));
    }
}
