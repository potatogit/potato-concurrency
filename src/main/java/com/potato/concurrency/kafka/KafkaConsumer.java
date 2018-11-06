package com.potato.concurrency.kafka;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class KafkaConsumer {

    @KafkaListener(id="test", topics={"topic-test"})
    public void listen(String data) {
        log.info("data = {}", data);
    }

}
