package exchange.rate;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.io.IOException;

@ActivityInterface
public interface RateService {
    @ActivityMethod
    double getRateApiCall() throws IOException;
}
