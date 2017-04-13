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
    private Date mEndDate;
    private ScheduleList mScheduleList;
    private Series mSeries;

    public TimeScheduler(ScheduleList scheduleList, Series series, Date startDate) {
        mScheduleList=scheduleList;
        mRemainingTime=series.getTotalTime();

        mDate =startDate;

    }

    public Date getEndDate() {
        Iterator<Schedule> schedule= mScheduleList.iterator();
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.setGregorianChange(mDate);

        while (mRemainingTime <= 0) {
            if (schedule.hasNext() == false) {
                schedule=mScheduleList.iterator();
            }
            int currentFreeTime=schedule.next().getFreeTime();
            mRemainingTime-=currentFreeTime;
            endDate.add(Calendar.MINUTE, currentFreeTime);
        }
        return endDate.getTime();
    }
}
