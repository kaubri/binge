package com.example.android.binge;

import java.util.Date;
import java.util.List;

/**
 * Should be implement by a class that get information about a series.
 *
 * @since 2017-04-10
 * @author Donovan J. Wilder
 * @version 1.0
 */

public interface SeriesGrabber {
    public List<Episode> getEpisodes();

    public String getTitle();
    public String getSynopsis();
    public int getNumberOfEpisodes();
    public int getNumberOfSeasons();
    public Series.Status getStatus();
    public Date getStartDate();
    public Date getEndDate();
}
