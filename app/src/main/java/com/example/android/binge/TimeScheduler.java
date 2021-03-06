package com.example.android.binge;

import android.content.Context;
import android.hardware.ConsumerIrManager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Created by dwilder1181 on 4/12/2017.
 * There are a couple of ways that you can dedduct te time
 */

public class TimeScheduler {

    /**
     * Returns the date you will complete a series base on how many episodes you watch in a day or week.
     */
    public static Date getFrequencyEndDate(Series series, Date startDate, String frequency, int episodesWatched, Context packageContext) {
        frequency= frequency.toLowerCase();
        int totalEpisodes= series.getNumberOfEpisodes();
        GregorianCalendar startCalendar= new GregorianCalendar();
        startCalendar.setTime(startDate);

        if (frequency.equals("day")) {
            while (totalEpisodes > 0) {
                totalEpisodes-=episodesWatched;
                startCalendar.add(Calendar.DAY_OF_MONTH,1);
            }
        }

        if (frequency.equals("week")) {
            while (totalEpisodes > 0) {
                totalEpisodes-=episodesWatched;
                startCalendar.add(Calendar.WEEK_OF_MONTH,1);
            }
        }
        return  startCalendar.getTime();

    }


}
