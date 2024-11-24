package com.moynes.temporal.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.moynes.temporal.example.model.User;
import com.moynes.temporal.example.workflow.ExampleWorkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class Runner implements CommandLineRunner {
    @Autowired
    WorkflowClient client;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Starting...");
        ExampleWorkflow workflow = client.newWorkflowStub(
                ExampleWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("TaskQueue")
                        .setWorkflowId("HelloSample")
                        .build());
        workflow.launchWorkflow(new User("first", "last", "2024-11-23T22:25:00.00Z"));
        log.debug("...Completed");
    }

}
