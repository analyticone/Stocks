package com.example.stocktracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.stocktracker.StockAdapter.StockAdapter;
import com.example.stocktracker.models.Stock;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    String myStocks = "AAPL,FB,GOOG,T,NOK,PLTR,TWTR,BAC,AMD,K";

    List<Stock> stocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stocks = new ArrayList<>();

        // Adapter Setup
        RecyclerView rvStocks = findViewById(R.id.rvStocks);
        // create the adapter
        StockAdapter adapter = new StockAdapter(this, stocks);

        // set the adpater on recyclerview
        rvStocks.setAdapter(adapter);

        // set a layout manager on the adapter
        rvStocks.setLayoutManager(new LinearLayoutManager(this));

        // https://github.com/codepath/CPAsyncHttpClient

        AsyncHttpClient client = new AsyncHttpClient();

         String API_KEY=getString(R.string.api_key);
         String url = "https://financialmodelingprep.com/api/v3/quote/" + myStocks +  "?apikey=" + API_KEY;

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.i("MainActivity", json.toString());
                try {
                    stocks.addAll(Stock.fromJSonArray(json.jsonArray));
                    Log.d("MainActivity", "Stocks: " + Stock.listToString(stocks));
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e("MainAvtivity", "Hit Json exception", e);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
    });

    }
}