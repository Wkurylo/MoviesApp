package com.example.wojtekkurylo.moviesapp;

/**
 * Created by wojtekkurylo on 01.09.2017.
 */

public class MovieComponent {

    private String mTitle;
    private String mReleaseDate;
    private String mPosterUrl;         // Drawable Resource ID
    private String mAverage;
    private String mPlotSynopsis;

//    public MovieComponent(String posterUrl)
//    {
////        mTitle = title;
////        mReleaseDate = releaseDate;
//        mPosterUrl = posterUrl;
////        mAverage = average;
////        mPlotSynopsis = plot;
//    }

    public void setMovieImageUrl(String movieImageurl) {
        mPosterUrl = movieImageurl;
    }

    public String getTitle()
    {
        return mTitle;
    }

    public String getReleaseDate()
    {
        return mReleaseDate;
    }

    public String getPosterUrl()
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
