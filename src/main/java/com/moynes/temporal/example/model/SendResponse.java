package com.moynes.temporal.example.model;

import org.springframework.kafka.support.SendResult;

import lombok.Data;

@Data
public class SendResponse {
    private String topic;
    private int partition;
    private long offset;

    public SendResponse(SendResult<String, User> metadata) {
        topic = metadata.getRecordMetadata().topic();
        partition = metadata.getRecordMetadata().partition();
        offset = metadata.getRecordMetadata().offset();
    }

}
