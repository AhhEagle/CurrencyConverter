package com.oladimeji.currencyconverter.Main;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.oladimeji.currencyconverter.R;

/**
 * Created by Oladimeji on 11/5/2017.
 */


public class CardsMain extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton showAddButton;
    private FloatingActionButton addBitcoinsButton;
    private FloatingActionButton addEthereumButton;

    private Spinner bitcoinsCurrencySpinner;
    private Spinner ethereumCurrencySpinner;

    private ArrayAdapter<CharSequence> currenciesAdapter;

    private LinearLayout cardList;
    private LinearLayout emptyStateView;

    private boolean isShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doActivityTransition();
        setContentView(R.layout.cards);


        showAddButton = (FloatingActionButton) findViewById(R.id.show_add_button);
        showAddButton.setOnClickListener(this);


        addBitcoinsButton = (FloatingActionButton) findViewById(R.id.add_bitcoins_card_button);
        addBitcoinsButton.setOnClickListener(this);


        addEthereumButton = (FloatingActionButton) findViewById(R.id.add_ethereum_card_button);
        addEthereumButton.setOnClickListener(this);

        currenciesAdapter = ArrayAdapter.createFromResource(this, R.array.currencies, R.layout.spinner);
        currenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        cardList = (LinearLayout) findViewById(R.id.cards_list);


        emptyStateView = (LinearLayout) findViewById(R.id.empty_state_view);
        emptyStateView.setVisibility(View.VISIBLE);

    }



    @TargetApi(21)
    @Override
    public void onClick(View v) {
        String selectedCurrency = "";
        switch (v.getId()) {

            case R.id.show_add_button:
                if(isShown) {
                    YoYo.with(Techniques.FadeOutDown).duration(500).playOn(addEthereumButton);
                    addEthereumButton.setVisibility(View.GONE); // Hide the Bitcoins button
                    YoYo.with(Techniques.FadeOutDown).duration(500).playOn(addBitcoinsButton);
                    addBitcoinsButton.setVisibility(View.GONE); // Hide the Ethereum button
                    isShown = false; // Digital currencies are no more visible
                    break;
                } else {
                    YoYo.with(Techniques.FadeInUp).duration(500).playOn(addEthereumButton);
                    addEthereumButton.setVisibility(View.VISIBLE); // Show the Bitcoins button
                    YoYo.with(Techniques.FadeInUp).duration(500).playOn(addBitcoinsButton);
                    addBitcoinsButton.setVisibility(View.VISIBLE); // Show the Ethereum button
                    isShown = true; // Digital currencies are now visible
                    break;
                }


            case R.id.add_bitcoins_card_button:
                emptyStateView.setVisibility(View.GONE); // Hide the empty state view
                View bitcoinCard = getLayoutInflater().inflate(R.layout.bitcoincard, cardList, false); // Inflate the card
                cardList.addView(bitcoinCard); // Card is added to its parent layout
                YoYo.with(Techniques.SlideInDown).duration(500).playOn(bitcoinCard);
                addBitcoinsButton.setEnabled(false); // Prevent further clicks
                bitcoinCard.setOnClickListener(this); // Listen for a click on this card
                bitcoinsCurrencySpinner = (Spinner) bitcoinCard.findViewById(R.id.bitcoin_card_spinner); // Get a reference to the spinner
                bitcoinsCurrencySpinner.setAdapter(currenciesAdapter);// Sets its data source
                break;
            case R.id.add_ethereum_card_button:
                emptyStateView.setVisibility(View.GONE); // Hide the empty state view
                View ethereumCard = getLayoutInflater().inflate(R.layout.ethereumcard, cardList, false); // Inflate the card
                cardList.addView(ethereumCard); // Card is added to its parent layout
                YoYo.with(Techniques.SlideInDown).duration(500).playOn(ethereumCard);
                addEthereumButton.setEnabled(false); // Prevent further clicks
                ethereumCard.setOnClickListener(this); // Listen for a click on this card
                ethereumCurrencySpinner = (Spinner) ethereumCard.findViewById(R.id.ethereum_card_spinner); // Get a reference to the spinner
                ethereumCurrencySpinner.setAdapter(currenciesAdapter);// Sets its data source
                break;

            case R.id.bitcoin_card:
                selectedCurrency = (String) bitcoinsCurrencySpinner.getSelectedItem();// Get selected currency_background_wall
                Intent intent1 = new Intent(this, ConversionMain.class);
                intent1.putExtra("selectedCurrency", selectedCurrency);// Add the selected currency_background_wall to the intent
                intent1.putExtra("selectedCard", "BITCOIN");//  Add the name of the selected card to the intent
                startActivity(intent1);// launch the conversion intent
                break;
            case R.id.ethereum_card:
                selectedCurrency = (String) ethereumCurrencySpinner.getSelectedItem();// Get selected currency_background_wall
                Intent intent2 = new Intent(this, ConversionMain.class);
                intent2.putExtra("selectedCurrency", selectedCurrency);// Add the selected currency_background_wall to the intent
                intent2.putExtra("selectedCard", "ETHEREUM");//  Add the name of the selected card to the intent
                startActivity(intent2);// launch the conversion intent
                break;
        }
    }

    @TargetApi(21)
    public void doActivityTransition() {
        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Slide(Gravity.END));
        }

    }

}
