package com.example.wojtekkurylo.moviesapp;

/**
 * Created by wojtekkurylo on 01.09.2017.
 */

public class MovieComponent {

    private static String mTitle;                                       // String
    private static String mReleaseDate;                                // String
    private static String mPosterUrl;         // Drawable Resource ID  // String or null !
    private static Double mAverage;                                    // number
    private static String mPlotSynopsis;                                // String

    public MovieComponent(String title, String releaseDate, String posterUrl, Number average, String plot)
    {
        mTitle = title;
        mReleaseDate = releaseDate;
        String firstPart = "http://image.tmdb.org/t/p/w185/";
        mPosterUrl = firstPart + posterUrl;
        // received from JSON: /nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        // target: http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        mAverage = average.doubleValue();
        mPlotSynopsis = plot;
    }



    public static String getTitle()
    {
        return mTitle;
    }

    public static String getReleaseDate()
    {
        return mReleaseDate;
    }

    public void setMovieImageUrl(String movieImageurl) {
        mPosterUrl = movieImageurl;
    }

    public static String getPosterUrl()
    {
        return mPosterUrl;
    }

    public void setMovieAverage(Number average) {
        mAverage = average.doubleValue();
    }

    public static Double getAverage()
    {
        return mAverage;
    }

    public void setPlotSynopsis(String synopsis)
    {
        mPlotSynopsis = synopsis;
    }

    public static String getPlotSynopsis()
    {
        return mPlotSynopsis;
    }
}
