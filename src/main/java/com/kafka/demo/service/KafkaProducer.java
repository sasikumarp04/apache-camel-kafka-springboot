package com.kafka.demo.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaProducer {

    private KafkaTemplate<String, GenericRecord> kafkaTemplate;

    public void sendMessage(GenericRecord message, String topicName) {

        log.info("Kafka Message [{}], topic [{}]", message, topicName);
        kafkaTemplate.send(topicName, message);
    }
}
