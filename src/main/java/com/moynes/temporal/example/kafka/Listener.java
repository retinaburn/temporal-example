package com.moynes.temporal.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.moynes.temporal.example.model.User;
import com.moynes.temporal.example.workflow.ExampleWorkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Listener {

    @Autowired
    WorkflowClient client;

    @KafkaListener(topics = "${topics.input}")
    public void listener(ConsumerRecord<String, User> record) {
        log.debug("Listener read: {}", record);

        // Create an instance that connects to a Temporal Service running on the local
        // machine, using the default port (7233)
        WorkflowServiceStubs serviceStub = WorkflowServiceStubs.newLocalServiceStubs();

        // Initialize the Temporal Client
        // This application uses the Client to communicate with the local Temporal
        // Service
        WorkflowClient client = WorkflowClient.newInstance(serviceStub);

        ExampleWorkflow workflow = client.newWorkflowStub(ExampleWorkflow.class, WorkflowOptions.newBuilder()
                .setTaskQueue("TaskQueue")
                .setWorkflowId(record.topic()+"-"+
                record.partition()+":"+record.offset())
                .build());

        // use WorkflowClient.execute to return future that contains Workflow result or
        // failure, or
        // use WorkflowClient.start to return WorkflowId and RunId of the started
        // Workflow).
        workflow.launchWorkflow(record.value());

        log.debug("Workflow result");

    }
}