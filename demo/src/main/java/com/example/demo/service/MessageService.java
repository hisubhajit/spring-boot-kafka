package com.example.demo.service;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    public boolean publishMessage(String topic, String message){
        try{
            kafkaTemplate.send(topic,message);
            return true;
        }catch(Exception e){
            LOG.error(e.getMessage());
            return false;
        }
    }
}
