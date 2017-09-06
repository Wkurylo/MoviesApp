package com.example.wojtekkurylo.moviesapp;

/**
 * Created by wojtekkurylo on 01.09.2017.
 */

public class MovieComponent {

    private String mTitle;                                       // String
    private String mReleaseDate;                                // String
    private String mPosterUrl;         // Drawable Resource ID  // String or null !
    private Double mAverage;                                    // number
    private String mPlotSynopsis;                                // String

//    public MovieComponent(String posterUrl)
//    {
////        mTitle = title;
////        mReleaseDate = releaseDate;
//        mPosterUrl = posterUrl;
////        mAverage = average;
////        mPlotSynopsis = plot;
//    }



    public String getTitle()
    {
        return mTitle;
    }

    public String getReleaseDate()
    {
        return mReleaseDate;
    }

    public void setMovieImageUrl(String movieImageurl) {
        mPosterUrl = movieImageurl;
    }
    public String getPosterUrl()
    {
        return mPosterUrl;
    }

    public void setMovieAverage(Number average) {
        mAverage = average.doubleValue();
    }
    public Double getAverage()
    {
        return mAverage;
    }

    public void setPlotSynopsis(String synopsis)
    {
        mPlotSynopsis = synopsis;
    }
    public String getPlotSynopsis()
    {
        return mPlotSynopsis;
    }
}
