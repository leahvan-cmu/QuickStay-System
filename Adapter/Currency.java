package Adapter;

public interface Currency {
    
    // Convert an amount in USD to this currency.
    double fromUsd(double amountInUsd);

    
    //Short currency code, "USD", "EUR".
    String getCode();

    
    //Symbol to display, "$", "€".
    String getSymbol();

    
    //format USD amount directly in this currency.
    default String formatFromUsd(double amountInUsd) {
        return String.format("%s%.2f", getSymbol(), fromUsd(amountInUsd));
    }
}


