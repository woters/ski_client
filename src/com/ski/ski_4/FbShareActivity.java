package com.ski.ski_4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Alyosha on 07.11.2014.
 */
public class FbShareActivity extends FragmentActivity {

    private FbShareFragment fbShareFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            fbShareFragment = new FbShareFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fbShareFragment)
                    .commit();
        } else {
            // Or set the fragment from restored state info
            fbShareFragment = (FbShareFragment) getSupportFragmentManager()
                    .findFragmentById(android.R.id.content);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
}