package com.example.android.stockwatch;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StockAdapter extends RecyclerView.Adapter<StockViewHolder>{

    private List<StockDetails> stockList;
    private MainActivity mainActivity;

    public StockAdapter(List<StockDetails> sList,MainActivity mActivity)
    {
        this.stockList = sList;
        this.mainActivity = mActivity;
    }


    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_details,parent,false);
        itemView.setOnClickListener(mainActivity);
        itemView.setOnLongClickListener(mainActivity);
        return new StockViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {

        StockDetails sd = stockList.get(position);
        if(sd.getChangePercentage() < 0) {
            setColorRed(holder);
        }
        else
        {
            setColorGreen(holder);
        }
        holder.stockSymbol.setText(sd.getStockSymbol());
        holder.cmpName.setText(sd.getCmpName());
        holder.price.setText(String.format(Locale.US, "%.2f", sd.getPrice()));
        holder.priceChange.setText(String.format(Locale.US, "%.2f", sd.getPriceChange()));
        holder.percentageChange.setText((String.format(Locale.US, "(%.2f%%)", sd.getChangePercentage())));

    }

    private void setColorRed(StockViewHolder holder)
    {
        holder.stockSymbol.setTextColor(Color.RED);
        holder.cmpName.setTextColor(Color.RED);
        holder.price.setTextColor(Color.RED);
        holder.priceChange.setTextColor(Color.RED);
        holder.percentageChange.setTextColor(Color.RED);
        holder.arrow.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp);
        holder.arrow.setColorFilter(Color.RED);
    }

    private void setColorGreen(StockViewHolder holder)
    {
        holder.stockSymbol.setTextColor(Color.GREEN);
        holder.cmpName.setTextColor(Color.GREEN);
        holder.price.setTextColor(Color.GREEN);
        holder.priceChange.setTextColor(Color.GREEN);
        holder.percentageChange.setTextColor(Color.GREEN);
        holder.arrow.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);
        holder.arrow.setColorFilter(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }
}
