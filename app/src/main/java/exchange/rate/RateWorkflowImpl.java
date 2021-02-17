package exchange.rate;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class RateWorkflowImpl implements RateWorkflow{
    ActivityOptions options = ActivityOptions.newBuilder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(20)).build();

    // an activity that retrieves the current exchange value from a remote endpoint
    private final RateService rateService = Workflow.newActivityStub(RateService.class, options);

    // an activity that gets the inverse value of the current rate from the api
    private final Rate rate = Workflow.newActivityStub(Rate.class, options);

    @Override
    public Double getRate() throws Exception {
        Double value = rateService.getRateApiCall();
        return rate.inverse(value);
    }

}
