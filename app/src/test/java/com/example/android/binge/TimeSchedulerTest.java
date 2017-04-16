package com.example.android.binge;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Donovan J. Wilder
 * @since  2017-04-16
 * @version 1.0
 */

public class TimeSchedulerTest {

    //TODO add a test for a schedule with time

    // Make sure that  the TimeScheduler can get an correct end date with an fully open schedule.
    @Test
    public void Given_24HourFreeTimeSchedule_And_48HourSeries_When_GetingAnEndDate_Then_EndDateShouldBe2DaysLater() {

        GregorianCalendar startDateCalander = new GregorianCalendar(2017, Calendar.APRIL, 13);
        Schedule schedule = new Schedule(new Date(), 0, -1);
        ScheduleList scheduleList = new ScheduleList(schedule);

        Series series = new Series("Bleach", "Ichigo saves rukia", 24, null, 24, 250, Time.hoursToMinutes(48), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime());
        System.out.println("StartDate is: " + startDateCalander.getTime());
        TimeScheduler timeScheduler=null;
        try {
            timeScheduler = new TimeScheduler(scheduleList, series, startDateCalander.getTime());
        } catch (LowFreeTimeException e) {
            System.out.println("No freetime");
        }
        Date endDate = timeScheduler.getEndDate();

        int minsBetweenDate = Time.getMinsFromDates(timeScheduler.getStartDate(), timeScheduler.getEndDate());
        System.out.println("EndDate is: " + timeScheduler.getEndDate());
        System.out.println("Raw time to watch is: " + Time.minsToBiggestUnitString(minsBetweenDate));
        System.out.println("Free time is: " + Time.minsToBiggestUnitString(schedule.getFreeTime()));
        System.out.println("Busy time is: " + Time.minsToBiggestUnitString(schedule.getBusyTime()));

        startDateCalander.add(Calendar.HOUR, 48);
        Assert.assertEquals("The Time calculated is not 24 hrs later.", startDateCalander.getTimeInMillis(), endDate.getTime());

    }

    /**
     * Make sure that the time scheduler calculate the end date using the free time in the schedule.
     */
    @Test
    public void Given_6HourFreeTimeSchedule_And_24HourSeries_When_GentingAnEndDate_Then_EndDateShouldBe4DaysLater() {
        GregorianCalendar startDateCalander = new GregorianCalendar(2017, Calendar.APRIL, 13);
        Schedule schedule = new Schedule(new Date(), Time.hoursToMinutes(18), -1);
        ScheduleList scheduleList = new ScheduleList(schedule);

        Series series = new Series("Bleach", "Ichigo saves rukia", 24, null, 24, 1, Time.hoursToMinutes(24), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime());
        System.out.println("StartDate is: " + startDateCalander.getTime());
        TimeScheduler timeScheduler = null;
        try {
            timeScheduler = new TimeScheduler(scheduleList, series, startDateCalander.getTime());
        } catch (LowFreeTimeException e) {
            e.printStackTrace();
        }
        Date endDate = timeScheduler.getEndDate();

        int minsBetweenDate = Time.getMinsFromDates(timeScheduler.getStartDate(), timeScheduler.getEndDate());
        System.out.println("EndDate is: " + timeScheduler.getEndDate());
        System.out.println("Raw time to watch is: " + Time.minsToBiggestUnitString(minsBetweenDate));
        System.out.println("Free time is: " + Time.minsToBiggestUnitString(schedule.getFreeTime()));
        System.out.println("Busy time is: " + Time.minsToBiggestUnitString(schedule.getBusyTime()));
        startDateCalander.add(Calendar.DAY_OF_MONTH, 4);
        Assert.assertEquals("The Time calculated is not 4 days later.", startDateCalander.getTimeInMillis(), endDate.getTime());
    }

    @Test
    public void Given_24HourFreeTimeSchedule_And_5EpisodeSeries24MinutesLong_And_ViewFrequencyOf2EpisodesADay_When_GettingAnEndDate_EndDateShouldBe3DaysLater() {
        GregorianCalendar startDateCalander = new GregorianCalendar(2017, Calendar.APRIL, 13);
        Schedule schedule = new Schedule(new Date(), 0, 2);
        ScheduleList scheduleList = new ScheduleList(schedule);

        Series series = new Series("Bleach", "Ichigo saves rukia", 5, null, 24, 1, Time.getTotalTime(5,24), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime());

        System.out.println("StartDate is: " + startDateCalander.getTime());
        TimeScheduler timeScheduler = null;
        try {
            timeScheduler = new TimeScheduler(scheduleList, series, startDateCalander.getTime());
        } catch (LowFreeTimeException e) {
            e.printStackTrace();
        }
        Date endDate = timeScheduler.getEndDate();

        int minsBetweenDate = Time.getMinsFromDates(timeScheduler.getStartDate(), timeScheduler.getEndDate());
        System.out.println("EndDate is: " + timeScheduler.getEndDate());
        System.out.println("Raw time to watch is: " + Time.minsToBiggestUnitString(minsBetweenDate));
        System.out.println("Free time is: " + Time.minsToBiggestUnitString(schedule.getFreeTime()));
        System.out.println("Busy time is: " + Time.minsToBiggestUnitString(schedule.getBusyTime()));
        startDateCalander.add(Calendar.DAY_OF_MONTH, 3);
        Assert.assertEquals("The Time calculated is not 3 days later.", startDateCalander.getTimeInMillis(), endDate.getTime());

    }

    @Test
    public void Given_1HourFreeTimeSchedule_And_5EpisodeSeries1Hr45MinsLong_ViewingFrequencyOf1EpisodeADay_When_GettingEndDate_Throw_NotEnoughFreeTimeException() {
        GregorianCalendar startDateCalander = new GregorianCalendar(2017, Calendar.APRIL, 13);
        Schedule schedule = new Schedule(new Date(), Time.getMins(0,23,0), 1);
        ScheduleList scheduleList = new ScheduleList(schedule);

        Series series = new Series("Bleach", "Ichigo saves rukia", 5, null, Time.getMins(0,1,45), 1, Time.getTotalTime(5,24), Series.Status.ONGOING, new GregorianCalendar(2011, 1, 15).getTime(), new GregorianCalendar(2017, 4, 11).getTime());

        System.out.println("StartDate is: " + startDateCalander.getTime());
        TimeScheduler timeScheduler = null;
        try {
            timeScheduler = new TimeScheduler(scheduleList, series, startDateCalander.getTime());
        } catch (LowFreeTimeException e) {
            e.printStackTrace();
            return;
        }

        Assert.assertTrue("Did not catch the LowFreeTimeExcepion.", false);
        Date endDate = timeScheduler.getEndDate();

        int minsBetweenDate = Time.getMinsFromDates(timeScheduler.getStartDate(), timeScheduler.getEndDate());
        System.out.println("EndDate is: " + timeScheduler.getEndDate());
        System.out.println("Raw time to watch is: " + Time.minsToBiggestUnitString(minsBetweenDate));
        System.out.println("Free time is: " + Time.minsToBiggestUnitString(schedule.getFreeTime()));
        System.out.println("Busy time is: " + Time.minsToBiggestUnitString(schedule.getBusyTime()));
        startDateCalander.add(Calendar.DAY_OF_MONTH, 3);
    }
}

