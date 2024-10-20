package com.moynes.temporal.example.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class ListenerConfig {


    @Value("${spring.kafka.listener.concurrency:1}")
    String concurrency;
    

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> factoryConfig() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(Integer.valueOf(concurrency));
        return factory;
    }
}
