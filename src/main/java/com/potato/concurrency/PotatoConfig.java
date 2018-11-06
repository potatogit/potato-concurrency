package com.potato.concurrency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.potato.concurrency.kafka.KafkaProducer;
import com.potato.concurrency.kafka.KafkaConsumer;

@Configuration
public class PotatoConfig {
    @Bean
    public KafkaProducer kafkaProducer() {
        return new KafkaProducer();
    }

    @Bean
    public KafkaConsumer testKafkaListener(){
        return new KafkaConsumer();
    }
}
