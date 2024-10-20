package com.moynes.temporal.example.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.moynes.temporal.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Generate {

    @Autowired
    DefaultKafkaProducerFactory<String, User> producerFactory;



    @PostMapping(value="/v1/generate")
    public ResponseEntity<String> writeToTopic(User user){
        KafkaProducer<String, User> producer = producerFactory.
        log.debug("Writing {} to topic", user);
        return ResponseEntity.ok("HI");
    }
}
