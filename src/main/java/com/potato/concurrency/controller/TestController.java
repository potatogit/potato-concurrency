package com.potato.concurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.potato.concurrency.kafka.KafkaProducer;

@Controller
public class TestController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "request success";
    }

    @RequestMapping("/kafkaTest")
    @ResponseBody
    public String send() {
        kafkaProducer.sendMessage("topic-test", "potato kafka test");
        return "message sent";
    }

}
