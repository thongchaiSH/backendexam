package com.app2.engine.model;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EmployeeDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
    private String phoneNumber;
    private String address;

}
