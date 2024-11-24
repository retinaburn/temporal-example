package com.moynes.temporal.example.workflow;

import java.time.Duration;

import com.moynes.temporal.example.activity.ExampleActivity;
import com.moynes.temporal.example.model.User;

import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WorkflowImpl(taskQueues = "TaskQueue")
public class ExampleWorkflowImpl implements ExampleWorkflow {

    ActivityOptions activityOptions = ActivityOptions.newBuilder()
        .setTaskQueue("TaskQueue")
        .setStartToCloseTimeout(Duration.ofMinutes(1))
        .build();

    @Override
    public void launchWorkflow(User user) {
        log.info("Workflow started");
    
        ExampleActivity activity = Workflow.newActivityStub(ExampleActivity.class, activityOptions);
        String uuid = activity.getUUID();

        String firstResponse = activity.firstActivity(uuid);

        String secondResponse = activity.secondActivity(uuid, firstResponse);
        log.debug("Second activity returned: {}, {}", secondResponse, uuid);

        activity.finalActivity(uuid);

        log.info("Workflow ended");
    }
    
}
