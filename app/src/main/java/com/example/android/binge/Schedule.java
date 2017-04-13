package com.example.android.binge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Model for a day schedule information
 *
 * @author Donovan J. Wilder
 * @since 2017-04-09
 * @version 1.0
 */

public class Schedule {
    int mWorkMinutes;
    int mSleepMinutes;
    int mSchoolMinutes;
    Date mDate;
    int mMiscMinutes;
//TODO create a validation to ensure that all non free time mins don't exceed that of the total day
    public Schedule() {

    }



    public Schedule(int workMinutes,
                    int sleepMinutes,
                    int schoolMinutes,
                    Date date,
                    int miscMinutes) {
        setWorkMinutes(workMinutes);
        setSleepMinutes(sleepMinutes);
        setSchoolMinutes(schoolMinutes);
        setDate(date);
        setMiscMinutes(miscMinutes);
    }

    public int getFreeTime() {
        GregorianCalendar currentDay = new GregorianCalendar();
        int freeTime= currentDay.getActualMaximum(Calendar.MINUTE);

        freeTime -= (getMiscMinutes() + getMiscMinutes() + getSchoolMinutes() + getSleepMinutes());
        return freeTime ;
    }
    public int getWorkMinutes() {
        return mWorkMinutes;
    }

    public void setWorkMinutes(int workMinutes) {
        mWorkMinutes = workMinutes;
    }

    public int getSleepMinutes() {
        return mSleepMinutes;
    }

    public void setSleepMinutes(int sleepMinutes) {
        mSleepMinutes = sleepMinutes;
    }

    public int getSchoolMinutes() {
        return mSchoolMinutes;
    }

    public void setSchoolMinutes(int schoolMinutes) {
        mSchoolMinutes = schoolMinutes;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getMiscMinutes() {
        return mMiscMinutes;
    }

    public void setMiscMinutes(int miscMinutes) {
        mMiscMinutes = miscMinutes;
    }
}
