package com.moynes.temporal.example.activity;

import java.util.UUID;

import org.springframework.stereotype.Service;

import io.temporal.spring.boot.ActivityImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActivityImpl(taskQueues = "TaskQueue")
public class ExampleActivityImpl implements ExampleActivity {

    @Override
    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        log.debug("UUID generated {}", uuid);
        return uuid;
    }

    @Override
    public String firstActivity(String uuid) {
        log.debug("First activity - {}", uuid);
        return "FirstResult";
    }

    @Override
    public String secondActivity(String uuid, String activityResult) {
        log.debug("Second activity - {}", uuid);
        return "SecondResult";
    }

    @Override
    public void finalActivity(String uuid) {
        log.debug("Final activity - {}", uuid);
        return;
    }

}
