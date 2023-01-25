package fr.iut;

import java.util.Locale;

public class App {
    
    public static void main(String[] args) {
        System.out.println(StringUtil.prettyCurrencyPrint(21500.390, Locale.FRANCE));
    }
    
}
