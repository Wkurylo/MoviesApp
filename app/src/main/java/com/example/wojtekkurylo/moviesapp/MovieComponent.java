package com.example.wojtekkurylo.moviesapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by wojtekkurylo on 01.09.2017.
 */

public class MovieComponent implements Parcelable {

    // (SECOND - 2)
    public static final Creator<MovieComponent> CREATOR = new Creator<MovieComponent>() {
        @Override
        public MovieComponent createFromParcel(Parcel in) {
            Log.d("MovieCmponent", "EXECUTING: MovieComponent createFromParcel(Parcel in)");
            return new MovieComponent(in);
        }

        // NOT EXECUTING
        @Override
        public MovieComponent[] newArray(int size) {
            Log.d("MovieCmponent", "EXECUTING: MovieComponent[] newArray(int size)");
            return new MovieComponent[size];
        }
    };
    private String mTitle;                                       // String
    private String mReleaseDate;                                // String
    private String mPosterUrl;         // Drawable Resource ID  // String or null !
    private Double mAverage;                                    // number
    private String mPlotSynopsis;                                // String


    public MovieComponent(String title, String releaseDate, String posterUrl, Number average, String plot) {
        mTitle = title;
        mReleaseDate = releaseDate;
        mPosterUrl = posterUrl;
        mAverage = average.doubleValue();
        mPlotSynopsis = plot;
    }

    // This is where you write the values you want to save to the `Parcel`. (FIRST - 1) & (FIRST - 4) & (FIRST - 5)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mReleaseDate);
        parcel.writeString(mPosterUrl);
        parcel.writeDouble(mAverage);
        parcel.writeString(mPlotSynopsis);
        Log.d("MovieCmponent", "EXECUTING: writeToParcel");
    }

    // NOT EXECUTING
    @Override
    public int describeContents() {
        Log.d("MovieCmponent", "EXECUTING: describeContents");
        return 0;
    }

    // Using the `in` variable, we can retrieve the values that
    // we originally wrote into the `Parcel`.  This constructor is usually
    // private so that only the `CREATOR` field can access.

    // (THIRD - 3)
    protected MovieComponent(Parcel in) {
        mTitle = in.readString();
        mReleaseDate = in.readString();
        mPosterUrl = in.readString();
        mAverage = in.readDouble();
        mPlotSynopsis = in.readString();
        Log.d("MovieCmponent", "EXECUTING: MovieComponent(Parcel in)");

    }

    public String getTitle() {
        return mTitle;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setMovieImageUrl(String movieImageurl) {
        mPosterUrl = movieImageurl;
    }

    public String getPosterUrlDetailActivity() {
        return mPosterUrl;
    }
    public String getPosterUrl() {
        String firstPart = "http://image.tmdb.org/t/p/w500/";
        String sentToMain = firstPart + mPosterUrl;
        // received from JSON: /nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        // target: http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        return sentToMain;
    }

    public void setMovieAverage(Number average) {
        mAverage = average.doubleValue();
    }

    public Double getAverage() {
        return mAverage;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public void setPlotSynopsis(String synopsis) {
        mPlotSynopsis = synopsis;
    }




}
