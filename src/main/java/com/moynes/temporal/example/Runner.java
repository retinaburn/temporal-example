package com.moynes.temporal.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

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
        // WorkflowClient client =
        // WorkflowClient.newInstance(WorkflowServiceStubs.newLocalServiceStubs());
        ExampleWorkflow workflow = client.newWorkflowStub(
                ExampleWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue("TaskQueue")
                        .setWorkflowId("HelloSample")
                        .build());
        workflow.launchWorkflow();
        log.debug("...Completed");
    }

}
