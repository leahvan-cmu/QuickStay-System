package Adapter;

public class GBPAdapter implements Currency {

    private final double rateFromUsd; //1 USD = 0.80 GBP

    public GBPAdapter(double rateFromUsd) {
        this.rateFromUsd = rateFromUsd;
    }

    @Override
    public double fromUsd(double amountInUsd) {
        return amountInUsd * rateFromUsd;
    }

    @Override
    public String getCode() {
        return "GBP";
    }

    @Override
    public String getSymbol() {
        return "£";
    }
}

