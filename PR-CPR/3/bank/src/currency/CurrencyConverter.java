package currency;

import java.util.EnumMap;
import java.util.Map;

public class CurrencyConverter {

    // kolik CZK stoji 1 jednotka dane meny (pevne kurzy)
    private final Map<Currency, Double> ratesToCzk = new EnumMap<>(Currency.class);

    public CurrencyConverter() {
        ratesToCzk.put(Currency.CZK, 1.0);
        ratesToCzk.put(Currency.EUR, 24.30);
        ratesToCzk.put(Currency.USD, 21.00);
    }

    public double convert(double amount, Currency from, Currency to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        if (from == to) {
            return amount;
        }

        double amountInCzk = amount * ratesToCzk.get(from);

        return amountInCzk / ratesToCzk.get(to);
    }
}
