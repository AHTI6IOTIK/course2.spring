package com.example.course2spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundEmployeesByDepartment extends RuntimeException {
    public NotFoundEmployeesByDepartment(int message) {
        super(String.format(
            "Не найдено сотрудников по департаменту #%s",
            message
        ));
    }
}
