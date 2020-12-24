package com.kafka.demo.controller;

import com.avro.demo.UserInfo;
import com.kafka.demo.controller.model.UserInfoDto;
import com.kafka.demo.service.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/kafka")
public class DemoController {

    private final KafkaProducer kafkaProducer;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("topicName") String topicName,
                                        @RequestBody final UserInfoDto userInfoDto) {
        UserInfo userInfo = getUserInfo(userInfoDto);
        kafkaProducer.sendMessage(userInfo, topicName);
    }

    private UserInfo getUserInfo(UserInfoDto userInfoDto) {
        return UserInfo.newBuilder()
                .setUserId(userInfoDto.getUserId())
                .setUserName(userInfoDto.getUserName()).build();
    }
}
