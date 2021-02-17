package exchange.rate;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class StartRateWorkflow {
    public static void main(String[] args) throws Exception {
        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(Definition.RATE_TASK_QUEUE)
                .build();

        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        RateWorkflow workflow = client.newWorkflowStub(RateWorkflow.class, options);

        // Get the rate value, will execute two activities
        String result = workflow.getRate().toString();
        System.out.println(result);
        System.exit(0);
    }
}
