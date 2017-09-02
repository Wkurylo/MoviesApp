package com.example.wojtekkurylo.moviesapp;

/**
 * Created by wojtekkurylo on 01.09.2017.
 */

public class MovieComponent {

    String mTitle;
    String mReleaseDate;
    int mPoster;         // Drawable Resource ID
    String mAverage;
    String mPlotSynopsis;

    public MovieComponent(String title, String releaseDate, int poster, String average, String plot)
    {
        mTitle = title;
        mReleaseDate = releaseDate;
        mPoster = poster;
        mAverage = average;
        mPlotSynopsis = plot;
    }

}
