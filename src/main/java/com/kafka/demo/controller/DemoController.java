package com.kafka.demo.controller;

import com.avro.demo.UserInfo;
import com.camel.demo.EmployeeDetailsV1;
import com.camel.demo.SalaryProcessV1;
import com.google.gson.Gson;
import com.kafka.demo.controller.model.EmployeeDetailsDto;
import com.kafka.demo.controller.model.SalaryProcessDto;
import com.kafka.demo.controller.model.UserInfoDto;
import com.kafka.demo.service.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.avro.generic.GenericRecord;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/kafka")
public class DemoController {

    private final KafkaProducer kafkaProducer;
    private final Gson gson;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("topicName") final String topicName,
                                        @RequestBody final String message) {
        GenericRecord genericRecord = getAvroMessageByTopic(topicName, message);
        kafkaProducer.sendMessage(genericRecord, topicName);
    }

    private GenericRecord getAvroMessageByTopic(@NonNull final String topicName, @NonNull final String message) {
        switch (topicName) {
            case "kafka-demo-test2":
                return buildUserInfo(message);
            case "employee-details-v1":
                return buildEmployeeDetails(message);
            case "salary-process-v1":
                return buildSalaryProcess(message);
            default:
                return null;
        }
    }

    private GenericRecord buildSalaryProcess(@NonNull final String message) {
        SalaryProcessDto salaryProcessDto = gson.fromJson(message, SalaryProcessDto.class);
        return SalaryProcessV1.newBuilder()
                .setCreatedDate(salaryProcessDto.getCreatedDate())
                .setCurrency(salaryProcessDto.getCurrency())
                .setEmployeeNumber(salaryProcessDto.getEmployeeNumber())
                .setStatus(salaryProcessDto.getStatus())
                .build();
    }

    private GenericRecord buildEmployeeDetails(@NonNull final String message) {
        EmployeeDetailsDto employeeDetailsDto = gson.fromJson(message, EmployeeDetailsDto.class);

        return EmployeeDetailsV1.newBuilder()
                .setCreatedDate(employeeDetailsDto.getCreatedDate())
                .setEmailId(employeeDetailsDto.getEmailId())
                .setEmployeeName(employeeDetailsDto.getEmployeeName())
                .setEmployeeNumber(employeeDetailsDto.getEmployeeNumber())
                .setMobileNumber(employeeDetailsDto.getMobileNumber())
                .setRole(employeeDetailsDto.getRole())
                .setUpdatedDate(employeeDetailsDto.getUpdatedDate())
                .build();
    }

    private UserInfo buildUserInfo(@NonNull final String message) {
        UserInfoDto userInfoDto = gson.fromJson(message, UserInfoDto.class);
        return UserInfo.newBuilder()
                .setUserId(userInfoDto.getUserId())
                .setUserName(userInfoDto.getUserName()).build();
    }
}
