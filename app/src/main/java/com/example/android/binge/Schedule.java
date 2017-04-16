package com.example.android.binge;

import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Model for a day schedule information. This version assumes a day has 24 hours. If Schedule is started on a partial
 * day it is best to get the rest of the free time.
 *
 * @author Donovan J. Wilder
 * @since 2017-04-09
 * @version 1.0
 */

public class Schedule {
    Date mDate;
    int mViewingFrequency;
    int mTimeBetweenShows;
    int mVariousBreaks;
    int mBusyTime;

    public int getBusyTime() {
        return mBusyTime;
    }

    public void setBusyTime(int busyTime) {
        mBusyTime = busyTime;
    }

    public int getVariousBreaks() {
        return mVariousBreaks;
    }

    public void setVariousBreaks(int variousBreaks) {
        mVariousBreaks = variousBreaks;
    }

    public int getTimeBetweenShows() {
        return mTimeBetweenShows;
    }

    public void setTimeBetweenShows(int timeBetweenShows) {
        mTimeBetweenShows = timeBetweenShows;
    }

    public Integer getViewingFrequency() {
        return mViewingFrequency;
    }

    public void setViewingFrequency(Integer viewingFrequency) {
        if (viewingFrequency == null) {
            return;
        }
        mViewingFrequency = viewingFrequency;
    }

    //TODO create a validation to ensure that all non free time mins don't exceed that of the total day
    public Schedule() {

    }

    /**
     * Creates an instance  of Schedule given the start date, hours to spend outside of binging and how many episode they
     * would like to watch per day.
     *
     * @param date The day to start the binge
     * @param busyTime The number of minutes that the users take to do personal things like sleep, work, eat, etc
     * @param viewingFrequency The number of episodes that the users will view for the day. A -1 means that there is no limit.
     */
    public Schedule(Date date, int busyTime, @Nullable Integer viewingFrequency) {
        setViewingFrequency(viewingFrequency);
        setBusyTime(busyTime);
        setDate(date);
    }

    /**
     * Returns the number of minutes the user can use to binge watch a seires. The value is based on
     * a 24 hour day.
     */
    public int getFreeTime() {
        //Old code
//        GregorianCalendar currentDay = new GregorianCalendar();
//        int freeTime= 1440;
//        freeTime -= (getMiscMinutes() + getMiscMinutes() + getSchoolMinutes() + getSleepMinutes());
//        System.out.println("Free time is: "+ freeTime);
//
//        return freeTime ;

        return (Time.hoursToMinutes(24) - getBusyTime());
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

}
