package Adapter;

public class CADAdapter implements Currency {

    private final double rateFromUsd; //1 USD = 1.36 CAD

    public CADAdapter(double rateFromUsd) {
        this.rateFromUsd = rateFromUsd;
    }

    @Override
    public double fromUsd(double amountInUsd) {
        return amountInUsd * rateFromUsd;
    }

    @Override
    public String getCode() {
        return "CAD";
    }

    @Override
    public String getSymbol() {
        return "C$";
    }
}
