package com.moynes.temporal.example.workflow;

import com.moynes.temporal.example.model.User;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ExampleWorkflow {
    @WorkflowMethod
    public void launchWorkflow(User user);
}
