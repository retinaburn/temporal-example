package com.moynes.temporal.example.workflow;

import io.temporal.common.interceptors.WorkflowInboundCallsInterceptor;
import io.temporal.common.interceptors.WorkflowInboundCallsInterceptorBase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkflowTokenInterceptor extends WorkflowInboundCallsInterceptorBase{

    public WorkflowTokenInterceptor(WorkflowInboundCallsInterceptor next){
        super(next);
    }

    @Override
    public WorkflowOutput execute(WorkflowInput input){
        log.debug("Take a token");
     try{
        return super.execute(input);
     }finally {
        log.debug("Return a token");
     }   
    }
}
