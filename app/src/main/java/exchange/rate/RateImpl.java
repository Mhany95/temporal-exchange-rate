package exchange.rate;

public class RateImpl implements  Rate{
    @Override
    public Double inverse(Double value) {
        return 1/value;
    }
}
