package com.kafka.demo.controller.model;

import lombok.Data;

@Data
public class SalaryProcessDto {

    String employeeNumber;
    String createdDate;
    String status;
    String currency;
}
