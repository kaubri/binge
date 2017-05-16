package com.example.android.binge;

/**
 * Created by dwilder1181 on 4/23/2017.
 */

class SavedSeries {
    private static final SavedSeries ourInstance = new SavedSeries();
    private Series mSeries;

    public Series getSeries() {
        return mSeries;
    }

    public void setSeries(Series series) {
        mSeries = series;
    }

    public static SavedSeries getOurInstance() {

        return ourInstance;
    }

    static SavedSeries getInstance() {
        return ourInstance;
    }

    private SavedSeries() {
    }
}
