package com.moynes.temporal.example.workflow;

import io.temporal.common.interceptors.WorkerInterceptorBase;
import io.temporal.common.interceptors.WorkflowInboundCallsInterceptor;

public class WorkerInterceptor extends WorkerInterceptorBase{
  @Override
  public WorkflowInboundCallsInterceptor interceptWorkflow(WorkflowInboundCallsInterceptor next) {
    return new WorkflowTokenInterceptor(next);
  }

}
