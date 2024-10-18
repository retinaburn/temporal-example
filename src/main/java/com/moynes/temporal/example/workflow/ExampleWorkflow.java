package com.moynes.temporal.example.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ExampleWorkflow {
    @WorkflowMethod
    public void launchWorkflow();
}
