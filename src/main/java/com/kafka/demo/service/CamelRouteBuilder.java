package com.kafka.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CamelRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        log.info("Inside router builder ");
        from("kafka:{{consumer.topic}}?brokers={{spring.kafka.bootstrap-servers}}&schemaRegistryURL={{spring.kafka.schema.registry.url}}&keyDeserializer=org.apache.kafka.common.serialization.StringDeserializer&valueDeserializer=io.confluent.kafka.serializers.KafkaAvroDeserializer")
                .log("Message received from Kafka : ${body}")
                .log("on the topic ${headers[kafka.TOPIC]}")
                .log("on the partition ${headers[kafka.PARTITION]}")
                .log("with the offset ${headers[kafka.OFFSET]}")
                .log("with the key ${headers[kafka.KEY]}")
                .process();
    }

}
