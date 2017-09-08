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

    public MovieComponent(String title, String releaseDate, String posterUrl, Number average, String plot)
    {
        mTitle = title;
        mReleaseDate = releaseDate;
        String firstPart = "http://image.tmdb.org/t/p/w185/";
        mPosterUrl = firstPart + posterUrl;
        // TODO: 08.09.2017 SPRAWDZIC czy String jest dobrze polaczony + wyciagnac URL z Obiektu MovieComponent 
        // dostane: /nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        // cel: http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        mAverage = average.doubleValue();
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
