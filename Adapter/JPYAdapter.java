package Adapter;

public class JPYAdapter implements Currency {
    private final double rateFromUsd;
    public JPYAdapter(double rateFromUsd) {
        this.rateFromUsd = rateFromUsd;
    }
    @Override
    public double fromUsd(double amountInUsd) {
        return amountInUsd * rateFromUsd;
    }
    @Override
    public String getCode() {
        return "JPY";
    }
    @Override
    public String getSymbol() {
        return "¥";
    }
}
