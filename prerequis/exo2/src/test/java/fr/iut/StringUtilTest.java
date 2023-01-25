package fr.iut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class StringUtilTest {
    @Test
    public void prettyCurrencyPrintTest() {
        String var = StringUtil.prettyCurrencyPrint(21500.390, Locale.FRANCE);
        String actual = "21\u00A0500,39\u00A0€";
        assertEquals(var, actual);
    }
}
