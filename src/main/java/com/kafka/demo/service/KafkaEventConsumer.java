package com.kafka.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class KafkaEventConsumer implements Processor {

    public void process(Exchange exchange) {

        GenericRecord genericRecord = (GenericRecord) exchange.getIn().getBody();

        log.info("Message Output : {}", genericRecord);
    }
}
