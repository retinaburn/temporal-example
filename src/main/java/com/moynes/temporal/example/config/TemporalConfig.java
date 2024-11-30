package com.moynes.temporal.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moynes.temporal.example.activity.ExampleActivityImpl;
import com.moynes.temporal.example.workflow.ExampleWorkflowImpl;
import com.moynes.temporal.example.workflow.WorkerInterceptor;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerFactoryOptions;

@Configuration
public class TemporalConfig {
    @Value("${temporal.connection.target}")
    String target;

    @Bean
    public WorkerFactory getWorkers(WorkflowClient workflowClient) {
        WorkerFactoryOptions workerFactoryOptions = WorkerFactoryOptions.newBuilder().setWorkerInterceptors(new WorkerInterceptor()).build();
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient, workerFactoryOptions);
        
        
        Worker worker = factory.newWorker("TaskQueue");

        worker.registerWorkflowImplementationTypes(ExampleWorkflowImpl.class);
        worker.registerActivitiesImplementations(new ExampleActivityImpl());
        
        

        factory.start();
        return factory;
    }


    @Bean
    public WorkflowClient getWorkflowClient() {
        WorkflowServiceStubsOptions workflowServiceStubsOptions = WorkflowServiceStubsOptions.newBuilder()
                .setTarget(target)
                .build();
        var workflowServiceStubs = WorkflowServiceStubs.newServiceStubs(workflowServiceStubsOptions);
        return WorkflowClient.newInstance(workflowServiceStubs);
    }

}
