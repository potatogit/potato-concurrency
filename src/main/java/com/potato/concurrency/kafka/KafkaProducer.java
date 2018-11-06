package com.potato.concurrency.kafka;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topicName, String data) {
        log.info("now send message to kafka");
        kafkaTemplate.send(topicName, data);
    }
}
