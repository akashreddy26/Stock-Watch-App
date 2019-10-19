package com.example.android.stockwatch;

import java.io.Serializable;

public class StockDetails implements Serializable {

    private String stockSymbol;
    private String cmpName;
    private double price;
    private double priceChange;
    private double changePercentage;


    public void setStockSymbol(String stockSymbol)
    {
        this.stockSymbol = stockSymbol;
    }

    public String getStockSymbol()
    {
        return stockSymbol;
    }

    public void setCmpName(String cmpName)
    {
        this.cmpName = cmpName;
    }

    public String getCmpName()
    {
        return cmpName;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPriceChange(double priceChange)
    {
        this.priceChange = priceChange;
    }

    public double getPriceChange()
    {
        return priceChange;
    }

    public void setChangePercentage(double changePercentage)
    {
        this.changePercentage = changePercentage;
    }

    public double getChangePercentage()
    {
        return changePercentage;
    }



}
