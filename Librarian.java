package com.library.model;

public class Librarian extends User {
    private String employeeCode;

    public Librarian(String id, String name, String employeeCode) {
        super(id, name);
        this.employeeCode = employeeCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        if (employeeCode == null || employeeCode.isBlank()) {
            throw new IllegalArgumentException("Employee code cannot be empty.");
        }
        this.employeeCode = employeeCode;
    }

    @Override
    public String getRole() {
        return "Librarian";
    }

    @Override
    public String getProfile() {
        return super.getProfile() + " | Employee Code: " + employeeCode;
    }
}
