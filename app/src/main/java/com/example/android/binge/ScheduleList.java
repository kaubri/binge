package com.example.android.binge;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Holds a list of schedules that are supposed to repeat.
 *
 * @author Donovan J. Wilder
 * @version 1.0
 * @since 2017-04-10
 */

public class ScheduleList implements Iterable<Schedule> {

    private List<Schedule> mScheduleList;

    public ScheduleList(Schedule... schedules) {
        mScheduleList = Arrays.asList(schedules);
    }
    @Override
    public Iterator<Schedule> iterator() {
        return mScheduleList.iterator();
    }
}
