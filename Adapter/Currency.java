package Adapter;

public interface Currency {
    
    // Convert an amount in USD to this currency.
    double fromUsd(double amountInUsd);

    
    //Short currency code, e.g., "USD", "EUR".
    String getCode();

    
    //Symbol to display, e.g., "$", "€".
    String getSymbol();

    
    //Helper to format a USD amount directly in this currency.
    default String formatFromUsd(double amountInUsd) {
        return String.format("%s%.2f", getSymbol(), fromUsd(amountInUsd));
    }
}
