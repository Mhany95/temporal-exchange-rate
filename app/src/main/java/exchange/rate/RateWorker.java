package exchange.rate;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class RateWorker {
    public static void main(String[] args) {
        // This gRPC stubs wrapper talks to the local docker instance of the Temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        // Create a Worker factory that can be used to create Workers that poll specific Task Queues.
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker(Definition.RATE_TASK_QUEUE);
        // This Worker hosts both Workflow and Activity implementations.
        // Workflows are stateful, so you need to supply a type to create instances.
        worker.registerWorkflowImplementationTypes(RateWorkflowImpl.class);

        // Activities are stateless and thread safe, so a shared instance is used.
        // register 2 activities
        worker.registerActivitiesImplementations(new RateImpl(), new RateServiceImpl());

        // Start polling the Task Queue.
        factory.start();
    }
}
