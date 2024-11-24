package com.moynes.temporal.example.controller;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.moynes.temporal.example.model.SendResponse;
import com.moynes.temporal.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Generate {
    @Value("${topics.input}")
    String inputTopic;

    @Autowired
    KafkaTemplate<String, User> template;

    @PostMapping(value="/v1/generate")
    public ResponseEntity<SendResponse> writeToTopic(
        @RequestBody User user) throws InterruptedException, ExecutionException{
        
        log.debug("Writing {} to topic", user);

        ProducerRecord<String, User> record = new ProducerRecord<String,User>(inputTopic, "key", user);
        SendResult<String, User> metadata = template.send(record).get();

        SendResponse response = new SendResponse(metadata);
        return ResponseEntity.ok(response);
    }

}

