package com.oladimeji.currencyconverter.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Oladimeji on 11/5/2017.
 */



public class Currency {


    private static List<HashMap<String, Double>> Currencies = new ArrayList<>();


    public static List<HashMap<String, Double>> getCurrencies() {
        return Currencies;
    }


    public static void setCurrencies(List<HashMap<String, Double>> digitalCurrencies) {
        Currency.Currencies = digitalCurrencies;
    }
}

