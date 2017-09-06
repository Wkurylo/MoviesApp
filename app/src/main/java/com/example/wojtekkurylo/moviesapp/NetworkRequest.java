package com.example.wojtekkurylo.moviesapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by wojtekkurylo on 06.09.2017.
 */

public class NetworkRequest {

    //https://api.themoviedb.org/3/movie/popular?api_key=ae1ff862219dfe7befa0155624128745
    //https://api.themoviedb.org/3/movie/top_rated?api_key=ae1ff862219dfe7befa0155624128745

    private static final String MOVIE_DB = "https://api.themoviedb.org/3/movie";
    private static final String API_KEY_KEY = "api_key";
    private static final String API_KEY_VALUE = BuildConfig.API_KEY;


    public static URL buildUrl(String moviePath) {
        Uri builtUri = Uri.parse(MOVIE_DB).buildUpon()
                .appendPath(moviePath)
                .appendQueryParameter(API_KEY_KEY, API_KEY_VALUE)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.e("NetworkRequest", "Error in buildURL Exception", e);
        }

        Log.d("NetworkRequest", "URL:  " + url);

        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        // If there is not url, return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        int httpResponseCode = urlConnection.getResponseCode();

        if (httpResponseCode == 200) {
            try {
                InputStream inputStream = urlConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    jsonResponse = scanner.next();
                    Log.d("Network Request", "JSON feedback: " + jsonResponse);
                    return jsonResponse;
                } else {
                    return jsonResponse; // else return empty String
                }
            } finally {
                urlConnection.disconnect();
            }
        } else {
            Log.e("Network Request", "httpResponseCode:" + httpResponseCode);
        }


        return jsonResponse;
    }

}
