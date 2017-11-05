package com.oladimeji.currencyconverter.Main;

/**
 * Created by Oladimeji on 11/5/2017.
 */

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.List;

import com.oladimeji.currencyconverter.Profile.NetworkCurrency;
import com.oladimeji.currencyconverter.R;
import com.oladimeji.currencyconverter.Profile.Currency;

public class SplashMain extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<HashMap<String, Double>>> {

    private CountDownTimer countDownTimer;
    private int loaderCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Initializes a new loader to perform the background network task
        getLoaderManager().initLoader(loaderCounter, null, SplashMain.this);


        countDownTimer = new CountDownTimer(15000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Do nothing
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alert = new AlertDialog.Builder(SplashMain.this);
                alert.setMessage(getString(R.string.countdown_message));
                alert.setPositiveButton(getString(R.string.positve_countdown_message), (dialog, which) -> {
                    loaderCounter++;// new loader
                    getLoaderManager().initLoader(loaderCounter, null, SplashMain.this);
                    countDownTimer.start();
                });

                alert.setNegativeButton(getString(R.string.negative_countdown_message), (dialog, which) -> {
                    countDownTimer.cancel();
                    finish();
                });
                alert.show();
            }
        }.start();
    }


    @Override
    public Loader<List<HashMap<String, Double>>> onCreateLoader(int id, Bundle args) {
        String REQUEST_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=AED,AUD,BRL,CAD,CHF,CNY,EGP,ETP,EUR,GBP,GHS,HKD,INR,KES,NGN,SEK,TZS,USD,XAF,ZAR";
        return new NetworkCurrency(this, REQUEST_URL);

    }


    /**
     * Called when a previously created loader has finished loading contents,
     * onLoadFinished, the returned data is passed as a value to a List<String, HashMap<String, Double>>
     * Then the @ConversionActivity is launched and this current activity is closed.
     *
     * @param loader ( The returned loader which was previously created)
     * @param data   ( The data that was returned)
     */
    @Override
    public void onLoadFinished(Loader<List<HashMap<String, Double>>> loader, List<HashMap<String, Double>> data) {
        if (!data.isEmpty() && data.size() != 0) {
            Currency.setCurrencies(data); // Store returned result
            Intent intent = new Intent(this, CardsMain.class);
            startActivity(intent); // Start Conversion Activity
            countDownTimer.cancel();// Cancel timer if still running
            finish(); // Close this activity
        }
    }

    @Override
    public void onLoaderReset(Loader<List<HashMap<String, Double>>> loader) {
        // Do nothing
    }

}


