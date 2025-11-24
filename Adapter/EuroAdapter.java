package Adapter;

public class EuroAdapter implements Currency {

    private final double rateFromUsd; //1 USD = 0.92 EUR

    public EuroAdapter(double rateFromUsd) {
        this.rateFromUsd = rateFromUsd;
    }

    @Override
    public double fromUsd(double amountInUsd) {
        return amountInUsd * rateFromUsd;
    }

    @Override
    public String getCode() {
        return "EUR";
    }

    @Override
    public String getSymbol() {
        return "€";
    }
}
