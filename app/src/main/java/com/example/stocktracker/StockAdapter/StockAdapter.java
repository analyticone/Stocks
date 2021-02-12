package com.example.stocktracker.StockAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocktracker.MainActivity;
import com.example.stocktracker.R;
import com.example.stocktracker.models.Stock;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder>{

    private List<Stock> stocks;

    public StockAdapter(MainActivity mainActivity, List<Stock> stocks)
    {
        this.stocks = stocks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View stockView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(stockView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stock stock = stocks.get(position);
        holder.bind(stock);

    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    //  https://guides.codepath.com/android/using-the-recyclerview#using-the-recyclerview

    /*
    Using a RecyclerView has the following key steps:

    Add RecyclerView AndroidX library to the Gradle build file
    Define a model class to use as the data source
    Add a RecyclerView to your activity to display the items
    Create a custom row layout XML file to visualize the item
    Create a RecyclerView.Adapter and ViewHolder to render the item
    Bind the adapter to the data source to populate the RecyclerView
     */

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvSymbol;
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvChange;

        public ViewHolder(View itemView)
        {
            super(itemView);

            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvChange = itemView.findViewById(R.id.tvChange);
        }

        public void bind(final Stock stock) {
            tvSymbol.setText(stock.getSymbol());
            tvName.setText(stock.getName());
            tvPrice.setText(String.format("$%.2f", stock.getPrice()));
            tvChange.setText("" + String.format("%.2f%%", stock.getChange()));
        }
    }
}
