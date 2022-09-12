package com.example.course2spring.model;

import java.util.Objects;

public class Department {
    private int departmentNum;

    public Department(int departmentNum) {
        this.departmentNum = departmentNum;
    }

    public int getDepartmentNum() {
        return departmentNum;
    }

    public Department setDepartmentNum(int departmentNum) {
        this.departmentNum = departmentNum;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return departmentNum == that.departmentNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentNum);
    }

    @Override
    public String toString() {
        return String.valueOf(departmentNum);
    }
}
