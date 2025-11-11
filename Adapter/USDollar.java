package Adapter;

public class USDollar implements Currency {

    @Override
    public double fromUsd(double amountInUsd) {
        return amountInUsd;
    }

    @Override
    public String getCode() {
        return "USD";
    }

    @Override
    public String getSymbol() {
        return "$";
    }
}

