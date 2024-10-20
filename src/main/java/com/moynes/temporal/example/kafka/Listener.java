package com.moynes.temporal.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import com.moynes.temporal.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Listener {

    @Autowired
    ConcurrentKafkaListenerContainerFactory<String, User> containerFactory;
    
    @KafkaListener(topics = "${topics.input}")
    public void listener(ConsumerRecord<String, String> record) {
        log.debug("Listener read: {}", record);
    }
}