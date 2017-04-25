package com.example.android.binge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by dwilder1181 on 4/19/2017.
 */

public class SeriesInfoActivity extends FragmentActivity {
    private static final String EXTRA_SERIES_SINGLETON_LIST_NUMBER = "com.example.android.binge.extra_series_singleton_list_number";

     public static Intent newIntent(Context packageContext, int seriesSingletonListNumber) {
         Intent i = new Intent(packageContext, SeriesInfoActivity.class);
         i.putExtra(EXTRA_SERIES_SINGLETON_LIST_NUMBER, seriesSingletonListNumber);

         return i;

     }

    public static int getSeriesSingletonListNumber(Intent intent) {
        int seriesSingletonListNumber=intent.getIntExtra(EXTRA_SERIES_SINGLETON_LIST_NUMBER, -1);
        return seriesSingletonListNumber;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        FragmentManager fm= getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new SeriesInfoFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();

        }
    }
}
