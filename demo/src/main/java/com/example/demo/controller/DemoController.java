package com.example.demo.controller;

import com.example.demo.service.MessageService;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MessageService.class);
    private static final String KAFKA_TOPIC_NAME = "quickstart-events";

    @Autowired
    MessageService messageService;

    @GetMapping("hellos")
    String sayHello() {
        return "hello!";
    }

    @GetMapping("message/send")
    void sendMessage(@RequestParam("msg") String message) {
        LOG.info("Message - {0}", message);
        messageService.publishMessage(KAFKA_TOPIC_NAME, message);
    }
}
