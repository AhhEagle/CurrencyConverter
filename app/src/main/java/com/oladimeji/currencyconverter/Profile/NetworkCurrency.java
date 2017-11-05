package com.oladimeji.currencyconverter.Profile;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Oladimeji on 11/5/2017.
 */




public class NetworkCurrency extends AsyncTaskLoader<List<HashMap<String, Double>>> {

    private String mUrl;

    public NetworkCurrency(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<HashMap<String, Double>> loadInBackground() {
        return NetworkUtils.getCurrency(mUrl);
    }
}
