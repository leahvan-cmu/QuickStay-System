package Adapter;

public class AUDAdapter implements Currency {
    private final double rateFromUsd;
    public AUDAdapter(double rateFromUsd) {
        this.rateFromUsd = rateFromUsd;
    }
    @Override
    public double fromUsd(double amountInUsd) {
        return amountInUsd * rateFromUsd;
    }
    @Override
    public String getCode() {
        return "AUD";
    }
    @Override
    public String getSymbol() {
        return "A$";
    }
}
