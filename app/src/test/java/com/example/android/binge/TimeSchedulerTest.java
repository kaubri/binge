package com.example.android.binge;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by dwilder1181 on 4/12/2017.
 */

public class TimeSchedulerTest {

    @Test
    public  void Given_24HourFreeTimeScheduleAndTwoDaySeries_When_GetingAEndDate_Then_EndDaateShouldBe48HrsLateer() {

        GregorianCalendar testStartDate= new GregorianCalendar(2017, Calendar.APRIL,13 );
        Schedule schedule= new Schedule(0,0,0,testStartDate.getTime(),0 );
        ScheduleList scheduleList = new ScheduleList(schedule);

        Series series = new Series("Bleach", "Ichigo saves rukia", 200, null, 8, 2880, Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime());

        TimeScheduler timeScheduler = new TimeScheduler(scheduleList, series, new GregorianCalendar(2017, 04, 13).getTime());
        System.out.println("The end date is: " + timeScheduler.getEndDate());
    }
}
