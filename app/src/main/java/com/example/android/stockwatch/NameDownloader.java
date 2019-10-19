package com.example.android.stockwatch;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NameDownloader extends AsyncTask<String, Void, String> {

    private static final String TAG = "NameDownloader";

    @SuppressLint("StaticFieldLeak")
    private MainActivity mActivity;
    public HashMap<String, String> stockData = new HashMap<>();
    String symbol;
    String name;

    private static final String DATA_URL = "https://api.iextrading.com/1.0/ref-data/symbols";

    NameDownloader(MainActivity ma)
    {
        mActivity = ma;
    }


    @Override
    protected String doInBackground(String... params) {

        String sym = params[0];

        Uri dataUri = Uri.parse(DATA_URL);
        String urlToUse = dataUri.toString();

        StringBuilder  sb = new StringBuilder();
        try {
            URL url = new URL(urlToUse);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null)
                sb.append(line);

        }
        catch (Exception e)
        {
            Log.d(TAG, "doInBackground: ", e);
            return null;
        }

        parseJson(sb.toString());
        return sym;


    }



    @Override
    protected void onPostExecute(String s)
    {
        mActivity.StockSymbols(s,getSymbols(s));





    }

    private void parseJson(String s)
    {
        try {

            JSONArray jsonArray = new JSONArray(s);

            for (int i=0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                 symbol = jsonObject.getString("symbol");
                 name = jsonObject.getString("name");

                stockData.put(symbol, name);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private HashMap<String,String> getSymbols(String s) {

       HashMap<String,String> getData = new HashMap<>();
       try {
           for (Map.Entry<String, String> entry : stockData.entrySet()) {

               String s1 = (String) entry.getKey();
               if (s1.contains(s)) {
                   getData.put(entry.getKey(), entry.getValue());
               }

           }
           return getData;
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return null;


    }

}
