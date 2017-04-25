package com.example.android.binge;

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
    private int mRemainingTime;
    private Date mStartDate;
    private ScheduleList mScheduleList;
    private Series mSeries;
    private Date mEndDate;

    public static Date getFrequencyEndDate(Series series, Date startDate, String frequency, int episodesWatched) {
        frequency= frequency.toLowerCase();
        int totalEpisodes= series.getNumberOfEpisodes();
        GregorianCalendar startCalendar= new GregorianCalendar();
        startCalendar.setTime(startDate);

        if (frequency.equals("daily")) {
            while (totalEpisodes > 0) {
                totalEpisodes-=episodesWatched;
                startCalendar.add(Calendar.DAY_OF_MONTH,1);
            }
        }

        if (frequency.equals("weekly")) {
            while (totalEpisodes > 0) {
                totalEpisodes-=episodesWatched;
                startCalendar.add(Calendar.WEEK_OF_MONTH,1);
            }
        }
        return  startCalendar.getTime();

    }
    public TimeScheduler(ScheduleList scheduleList, Series series, Date startDate) throws LowFreeTimeException {
        setScheduleList(scheduleList);
        setRemainingTime(series.getTotalTime());
        setStartDate(new Date(startDate.getTime()));
        setEndDate(new Date(startDate.getTime()));
        mSeries=series;
        calcEndDate();

    }

    public Series getSeries() {
        return mSeries;
    }

    public void setSeries(Series series) {
        mSeries = series;
    }

    public String getRawTime() {
        return Time.minsToBiggestUnitString(getSeries().getTotalTime());

    }
    //TODO make various versions of get Endate

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date endDate) {
        mEndDate = endDate;
    }

    private void calcEndDate() throws LowFreeTimeException {

        Iterator<Schedule> scheduleIterator = getScheduleList().iterator();
        GregorianCalendar endDateCalendar = new GregorianCalendar();
        endDateCalendar.setTime(getEndDate());

        while (getRemainingTime() > 0) {
            if (scheduleIterator.hasNext() == false) {
                scheduleIterator = getScheduleList().iterator();
            }
            Schedule currentSchedule= scheduleIterator.next();
            int dayFreeTime = currentSchedule.getFreeTime();

            if(currentSchedule.getViewingFrequency()>-1){

                int maxViewingTime=(currentSchedule.getViewingFrequency()*mSeries.getAverageEpisodeLength());
                if (maxViewingTime > currentSchedule.getFreeTime()) {
                    throw new LowFreeTimeException("FreeTime: " +currentSchedule.getFreeTime()+" Viewing time needed: " + maxViewingTime +" Viewing Frequency: " +currentSchedule.getViewingFrequency()+ " Average episode length: " + getSeries().getAverageEpisodeLength());
                }
                setRemainingTime(getRemainingTime()-maxViewingTime);
            }else {
                setRemainingTime(getRemainingTime()- dayFreeTime);
            }
            //System.out.println("Calc Remainign Time: " + mRemainingTime);
            endDateCalendar.add(Calendar.DATE, 1);


        }
         setEndDate(endDateCalendar.getTime());
    }
    public int getRemainingTime() {
        return mRemainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        mRemainingTime = remainingTime;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public ScheduleList getScheduleList() {
        return mScheduleList;
    }

    public void setScheduleList(ScheduleList scheduleList) {
        mScheduleList = scheduleList;
    }

}
