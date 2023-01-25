package fr.iut;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class StringUtil {
    public static String prettyCurrencyPrint (final double amount, final Locale locale) {
        // Get the currency instance
        NumberFormat nF = NumberFormat.getCurrencyInstance();
        // Sets the currency to locale given in parameter
        nF.setCurrency(Currency.getInstance(locale));
        return nF.format(amount);
    }
}
