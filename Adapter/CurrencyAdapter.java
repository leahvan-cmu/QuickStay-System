package Adapter;

public class CurrencyAdapter {

    // found rates for 11/11/2025
    private static final double USD_TO_EUR = 0.86;
    private static final double USD_TO_GBP = 0.76;
    private static final double USD_TO_CAD = 1.40;
    private static final double USD_TO_AUD = 1.53;
    private static final double USD_TO_JPY = 154.0;

    public static Currency get(String code) {
        if (code == null) {
            return new USDollar();
        }
        switch (code.toUpperCase()) {
            case "EUR":
            	return new EuroAdapter(USD_TO_EUR);
            case "GBP":
            	return new GBPAdapter(USD_TO_GBP);
            case "CAD":
            	return new CADAdapter(USD_TO_CAD);
            case "AUD":
            	return new AUDAdapter(USD_TO_AUD);
            case "JPY":
            	return new JPYAdapter(USD_TO_JPY);
            case "USD":
            default:   return new USDollar();
        }
    }
}
