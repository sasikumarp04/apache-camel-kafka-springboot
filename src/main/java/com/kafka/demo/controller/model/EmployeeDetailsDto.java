package com.kafka.demo.controller.model;

import lombok.Data;

@Data
public class EmployeeDetailsDto {

    String employeeName;
    String employeeNumber;
    String role;
    String createdDate;
    String updatedDate;
    String mobileNumber;
    String emailId;
}
