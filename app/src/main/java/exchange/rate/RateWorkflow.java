package exchange.rate;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface RateWorkflow {
    // Note: a workflow can have one workflowMethod per interface
    @WorkflowMethod
    Double getRate() throws Exception;
}
