package com.example.android.binge;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by dwilder1181 on 4/12/2017.
 */

public class TimeSchedulerTest {

    //TODO add a test for a schedule with time

    @Test
    public  void Given_24HourFreeTimeScheduleAndTwoDaySeries_When_GetingAEndDate_Then_EndDaateShouldBe48HrsLateer() {

        GregorianCalendar testStartDate= new GregorianCalendar(2017, Calendar.APRIL,13 );
        Schedule schedule= new Schedule(0,0,0,testStartDate.getTime(),0 );
        ScheduleList scheduleList = new ScheduleList(schedule);

        Series series = new Series("Bleach", "Ichigo saves rukia", 200, null, 8, 72, Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime());
        System.out.println("StartDate is: " + testStartDate.getTime());
        TimeScheduler timeScheduler = new TimeScheduler(scheduleList, series,testStartDate.getTime());

        System.out.println("EndDate is: " + timeScheduler.getEndDate());



    }
}
