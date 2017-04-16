package com.example.android.binge;

import java.util.Date;

/**
 *
 * Holds common units of time for the app, Days, hours, mins.
 *
 * A utility that helps with common conversions and calculations that have to do with time.
 *
 * @author Donovan J. Wilder
 * @since 2017-04-16
 * @version 1.0
 */

public class Time {

    private int mDays;
    private int mHours;
    private int mMins;



    public int getDays() {
        return mDays;
    }

    public void setDays(int days) {
        mDays = days;
    }
    //TODO Create a unit test for this class


    public static int hoursToMinutes(int hours){
        return hours * 60;
    }

    public static int daystToMinutes(int days) {
        return (days * 24 * 60);
    }


    public static String minsToBiggestUnitString(int minutes) {
        int hours= minutes/60;
        int remainingMins= minutes%60;
        int remainingHours= hours%24;
        int days = hours / 24;

        StringBuilder timeString = new StringBuilder();


        timeString.append(days + " days ");
        timeString.append(remainingHours + " hrs ");
        timeString.append(remainingMins + " mins ");

        return timeString.toString();
    }

    public static int millisecsToMins(long millisecs) {
        long secs= millisecs/1000L;
        return  (int)secs/60 ;

    }

    public static int getMins(int days, int hours, int mins) {
        int time=(1440 * days);
        time += (60 * hours);
        time+=mins;

        return time;
    }
    public static int getTotalTime(int numberOfEpisodes, int averageLenght) {
        return numberOfEpisodes* averageLenght;
    }
    public static int getMinsFromDates(Date startDate, Date endDate) {
        int minsBetweenDate = Time.millisecsToMins(endDate.getTime() - startDate.getTime());
        return minsBetweenDate;
    }
}
