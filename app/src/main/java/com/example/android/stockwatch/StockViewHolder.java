package com.example.android.stockwatch;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StockViewHolder extends RecyclerView.ViewHolder {

    public TextView stockSymbol;
    public TextView cmpName;
    public TextView price;
    public TextView priceChange;
    public TextView percentageChange;
    public ImageView arrow;

    public StockViewHolder(@NonNull View itemView) {
        super(itemView);

        stockSymbol = itemView.findViewById(R.id.stockSymbol);
        cmpName = itemView.findViewById(R.id.cmpName);
        price = itemView.findViewById(R.id.price);
        priceChange = itemView.findViewById(R.id.priceChange);
        percentageChange = itemView.findViewById(R.id.percentageChange);
        arrow = itemView.findViewById(R.id.imageView);
    }
}
