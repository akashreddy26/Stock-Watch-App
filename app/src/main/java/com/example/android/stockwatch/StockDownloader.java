package com.example.android.stockwatch;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class StockDownloader extends AsyncTask<String, Void, String> {

    private static final String TAG = "StockDownloader";
    private MainActivity mActivity;

    private static final String dataURL = " https://cloud.iexapis.com/stable/stock/";

    private static final String myAPIKEY = "/quote?token=sk_2358849c1ffa4306a122b99b7375d6b5";


    StockDownloader(MainActivity ma)
    {
        mActivity = ma;
    }


    @Override
    protected String doInBackground(String... strings) {

        String fullURL = dataURL + strings[0] + myAPIKEY;

        Uri uri = Uri.parse(fullURL);
        String urlToUse = uri.toString();

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlToUse);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            Log.d(TAG, "doInBackground: " + sb.toString());

        } catch (Exception e) {
            Log.e(TAG, "doInBackground: ", e);

        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        StockDetails sd = parseJson(s);
        mActivity.addStockToDB(sd);
    }


    public StockDetails parseJson(String s) {
        StockDetails sd = new StockDetails();
        try {

            JSONObject jsonObject = new JSONObject(s);
            String symbol = jsonObject.getString("symbol");
            String name = jsonObject.getString("companyName");
            double price = jsonObject.getDouble("latestPrice");
            double priceChange = jsonObject.getDouble("change");
            double changePercentage = jsonObject.getDouble("changePercent");

            sd.setStockSymbol(symbol);
            sd.setCmpName(name);
            sd.setPrice(price);
            sd.setPriceChange(priceChange);
            sd.setChangePercentage(changePercentage);

            return sd;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
