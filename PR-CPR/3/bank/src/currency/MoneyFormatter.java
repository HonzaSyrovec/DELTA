package currency;

import java.util.Locale;

public class MoneyFormatter {

    private final CurrencyConverter currencyConverter;

    // dalsi mena, na kterou se ve vypisech prepocitava
    private final Currency secondaryCurrency;

    // pouzije se, kdyz je ucet veden primo v secondaryCurrency
    private final Currency fallbackCurrency;

    public MoneyFormatter(CurrencyConverter currencyConverter, Currency secondaryCurrency, Currency fallbackCurrency) {
        this.currencyConverter = currencyConverter;
        this.secondaryCurrency = secondaryCurrency;
        this.fallbackCurrency = fallbackCurrency;
    }

    public String format(double amount, Currency currency) {
        Currency target = currency == secondaryCurrency ? fallbackCurrency : secondaryCurrency;
        double converted = currencyConverter.convert(amount, currency, target);

        return String.format(Locale.ROOT, "%.2f %s (~ %.2f %s)", amount, currency, converted, target);
    }
}
