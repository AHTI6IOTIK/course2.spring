package com.example.course2spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeAlreadyAddedException extends EmployeeNotFoundException{
    public EmployeeAlreadyAddedException(String fullName, String lastName) {
        super(String.format(
            "Сотрудник #%s %s, был добавлен ранее",
            fullName,
            lastName
        ));
    }
}
