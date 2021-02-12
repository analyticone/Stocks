package com.example.stocktracker.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    String symbol;
    String name;
    double price;
    double change;

    public Stock(JSONObject jsonObject) throws JSONException {
        symbol = jsonObject.getString("symbol");
        name = jsonObject.getString("name");
        price = jsonObject.getDouble("price");
        change = jsonObject.getDouble("change");
        Log.d("Stock", toString());
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", change=" + change +
                '}';
    }

    public static String listToString(List<Stock> stocks)
    {
        String result = "\n";
        for (Stock s : stocks)
            result += s.toString() +"\n";
        return result;
    }

    public static List<Stock> fromJSonArray(JSONArray jsonArray) throws JSONException {
        List<Stock> stocks = new ArrayList<>();

        for (int i=0; i<jsonArray.length(); i++)
            stocks.add(new Stock(jsonArray.getJSONObject(i)));

        return stocks;
    }
}
