package com.example.android.binge;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.List;

/**
 * Models the data of a Series.
 *
 * @author Donovan J. Wilder
 * @since 20017-04-09
 * @version 1.0
 */

public class Series {
    private String mTitle;
    private String mSynopsis;
    private int mNumberOfEpisodes;
    private int mTotalTime;

    public void setTotalTime(int totalTime) {
        mTotalTime = totalTime;
    }

    private List<Episode> mEpisodeList;
    private int mNumberOfSeasons;
    private  Status mStatus;
    private Date mStartDate;
    private Date mEndDate;
    private SeriesGrabber mSeriesGrabber;
    private int mAverageEpisodeLength;
    private Bitmap mImage;

    public Bitmap getImage() {
        return mImage;
    }

    public void setImage(Bitmap image) {
        mImage = image;
    }

    public int getAverageEpisodeLength() {
        return mAverageEpisodeLength;
    }

    public void setAverageEpisodeLength(int averageEpisodeLength) {
        mAverageEpisodeLength = averageEpisodeLength;
    }

    public Series(SeriesGrabber seriesGrabber) {
        setTitle(seriesGrabber.getTitle());
        setSynopsis(seriesGrabber.getSynopsis());
        setNumberOfEpisodes(seriesGrabber.getNumberOfEpisodes());
        setEpisodeList(seriesGrabber.getEpisodeList());
        setNumberOfSeasons(seriesGrabber.getNumberOfSeasons());
        setTotalTime(seriesGrabber.getTotalTime());
        setStatus(seriesGrabber.getStatus());
        setStartDate(seriesGrabber.getStartDate());
        setEndDate(seriesGrabber.getEndDate());
        setImage(seriesGrabber.getBitmapImage());
    }
    public Series( String title,
                   String synopsis,
                   int numberOfEpisodes,
                   List<Episode> episodeList,
                   int averageEpisodeTime,
                   int numberOfSeasons,
                   int totalTime,
                   Status status,
                   Date startDate,
                   Date endDate) {
        setTitle(title);
        setSynopsis(synopsis);
        setAverageEpisodeLength(averageEpisodeTime);
        setNumberOfEpisodes(numberOfEpisodes);
        setEpisodeList(episodeList);
        setNumberOfSeasons(numberOfSeasons);
        setTotalTime(totalTime);
        setStatus(status);
        setStartDate(startDate);
        setEndDate(endDate);
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSynopsis() {
        return mSynopsis;
    }

    public void setSynopsis(String synopsis) {
        mSynopsis = synopsis;
    }

    public int getNumberOfEpisodes() {
        return mNumberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        mNumberOfEpisodes = numberOfEpisodes;
    }

    public List<Episode> getEpisodeList() {
        return mEpisodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        mEpisodeList = episodeList;
    }

    public int getNumberOfSeasons() {
        return mNumberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        mNumberOfSeasons = numberOfSeasons;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date endDate) {
        mEndDate = endDate;
    }

    /**
     * Returns the total time of the series in minutes.
     */
    public int getTotalTime() {
        return mTotalTime  ;
    }


    public enum Status {
        ONGOING,COMPLETED;

        @Override
        public String toString() {
            switch (this) {
                case ONGOING:
                    return "ongoing";
                case COMPLETED:
                    return "completed";
                default:
                    return null;
            }
        }
    }
}

