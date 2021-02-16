package exchange.rate;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Rate {
    @ActivityMethod
    Double inverse(Double value);
}
