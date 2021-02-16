package exchange.rate;

public class RateImpl implements  Rate{
    @Override
    public Double inverse(Double value) throws Exception {
        if (value > 0)
            return 1 / value;
        else throw new Exception("Rate is equal to 0, can't find an inverse");
    }
}
