package com.example.wojtekkurylo.moviesapp;

/**
 * Created by wojtekkurylo on 01.09.2017.
 */

public class MovieComponent {

    String mTitle;
    String mReleaseDate;
    int mPosterUrl;         // Drawable Resource ID
    String mAverage;
    String mPlotSynopsis;

    public MovieComponent(String title, String releaseDate, int posterUrl, String average, String plot)
    {
        mTitle = title;
        mReleaseDate = releaseDate;
        mPosterUrl = posterUrl;
        mAverage = average;
        mPlotSynopsis = plot;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getReleaseDate()
    {
        return mReleaseDate;
    }

    public int getPosterUrl()
    {
        return mPosterUrl;
    }

    public String getAverage()
    {
        return mAverage;
    }

    public String getPlotSynopsis()
    {
        return mPlotSynopsis;
    }
}
