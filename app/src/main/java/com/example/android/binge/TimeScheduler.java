package com.example.android.binge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Created by dwilder1181 on 4/12/2017.
 */

public class TimeScheduler {
    private int mRemainingTime;
    private Date mDate;
    private ScheduleList mScheduleList;

    public TimeScheduler(ScheduleList scheduleList, Series series, Date startDate) {
        setScheduleList(scheduleList);
        setRemainingTime(series.getTotalTime());
        setDate(startDate);

    }

    //TODO make various versions of get Endate

    public Date getEndDate() {

        Iterator<Schedule> scheduleIterator = mScheduleList.iterator();
        GregorianCalendar dateCalendar = new GregorianCalendar();
        dateCalendar.setTime(mDate);

        while (mRemainingTime > 0) {
            if (scheduleIterator.hasNext() == false) {
                scheduleIterator = mScheduleList.iterator();
            }
            int dayFreeTime = scheduleIterator.next().getFreeTime();
            mRemainingTime-=  dayFreeTime;
            System.out.println("Calc Remainign Time: " + mRemainingTime);
            dateCalendar.add(Calendar.DATE, 1);


        }
        return dateCalendar.getTime();
    }
    public int getRemainingTime() {
        return mRemainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        mRemainingTime = remainingTime;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public ScheduleList getScheduleList() {
        return mScheduleList;
    }

    public void setScheduleList(ScheduleList scheduleList) {
        mScheduleList = scheduleList;
    }

}
