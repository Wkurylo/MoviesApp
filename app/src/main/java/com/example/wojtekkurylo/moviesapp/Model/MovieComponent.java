package com.example.wojtekkurylo.moviesapp.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * {@link MovieComponent} Custom class to store reference to movie details. The Class implements Parcelable
 */

public class MovieComponent implements Parcelable {

    // (SECOND - 2)
    public static final Creator<MovieComponent> CREATOR = new Creator<MovieComponent>() {
        @Override
        public MovieComponent createFromParcel(Parcel in) {
            Log.d("MovieComponent", "EXECUTING: MovieComponent createFromParcel(Parcel in)");
            return new MovieComponent(in);
        }

        // NOT EXECUTING
        @Override
        public MovieComponent[] newArray(int size) {
            Log.d("MovieComponent", "EXECUTING: MovieComponent[] newArray(int size)");
            return new MovieComponent[size];
        }
    };

    // @SerializedName correspond to JSON HTTP respond name-filed we are interested in.

    @SerializedName("title")
    private String mTitle;                                       // String

    @SerializedName("release_date")
    private String mReleaseDate;                                // String

    @SerializedName("poster_path")
    private String mPosterUrl;         // Drawable Resource ID  // String or null !

    @SerializedName("vote_average")
    private Double mAverage;                                    // number

    @SerializedName("overview")
    private String mPlotSynopsis;                                // String

    @SerializedName("results")
    private ArrayList<MovieComponent> mResults;

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
        Log.d("MovieComponent", "EXECUTING: writeToParcel");
    }

    // NOT EXECUTING
    @Override
    public int describeContents() {
        Log.d("MovieComponent", "EXECUTING: describeContents");
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
        Log.d("MovieComponent", "EXECUTING: MovieComponent(Parcel in)");

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
        // received from JSON: /nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        // target: http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        return firstPart + mPosterUrl;
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

    public ArrayList<MovieComponent> getResults() {
        return mResults;
    }




}
