package com.example.ski_4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;

import java.util.Locale;


public class MainActivity extends Activity implements OnClickListener {

    private Locale myLocale;
    private Button btn_1, btn_2, btn_en, btn_ru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btn_en = (Button)findViewById(R.id.btn_en);
        this.btn_ru = (Button)findViewById(R.id.btn_ru);
        this.btn_1 = (Button)findViewById(R.id.button1);
        this.btn_2 = (Button)findViewById(R.id.button2);


        this.btn_en.setOnClickListener(this);
        this.btn_en.setOnClickListener(this);
        this.btn_1.setOnClickListener(this);
        this.btn_2.setOnClickListener(this);


        loadLocale();
    }

    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }
    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        updateTexts();
    }

    private void updateTexts()
    {

        btn_1.setText(R.string.Back);
        btn_2.setText(R.string.Confirm);

    }

    @Override
    public void onClick(View v) {
        String lang = "en";
        switch (v.getId()) {
            case R.id.btn_en:
                lang = "en";
                break;
            case R.id.btn_ru:
                lang = "ru";
                break;

            default:
                break;
        }
        changeLang(lang);
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (myLocale != null){
            newConfig.locale = myLocale;
            Locale.setDefault(myLocale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        //return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                //GoToChooseDateBuy(View view);
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    /** Called when the user clicks the Sell button */
	/*public void GoToChooseDateBuy(View view) {
		Intent intentBuy = new Intent(this, ChooseDate.class);
		intentBuy.putExtra("runner", Constants.ACTIVITY_BUY);
		startActivity(intentBuy);
	}

	public void GoToChooseDateSell(View view) {
		Intent intentSell = new Intent(this, ChooseDate.class);
		intentSell.putExtra("runner", Constants.ACTIVITY_SELL);
		startActivity(intentSell);
	}*/

    public void GoToBuy(View view) {
        Intent intent = new Intent(this, Container_Buy.class);
        //intent.putExtra("runner", Constants.ACTIVITY_BUY);
        Constants.ACTIVITY=0;
        startActivity(intent);
    }

    public void GoToSell(View view) {
        Intent intent = new Intent(this, Container_Sell.class);
        //intent.putExtra("runner", Constants.ACTIVITY_SELL);
        Constants.ACTIVITY=1;
        startActivity(intent);
        //	ft.replace(R.id.your_placehodler, new YourFragment());
        //ft.commit();
    }

}