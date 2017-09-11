package com.example.wojtekkurylo.moviesapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieRecyclerAdapter.MovieAdapterOnClickHandler {

    private MovieRecyclerAdapter mMovieRecycleAdapter;
    private ArrayList<MovieComponent> mAllDataInArrayList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // recovering the instance state if exist
        if(savedInstanceState == null || !savedInstanceState.containsKey("mAllDataInArrayList"))
        {
            mAllDataInArrayList = new ArrayList<MovieComponent>();
        }else
        {
            mAllDataInArrayList = savedInstanceState.getParcelableArrayList("mAllDataInArrayList");
        }
        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_main.xml file
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        mMovieRecycleAdapter = new MovieRecyclerAdapter(this, MainActivity.this);
        mRecyclerView.setAdapter(mMovieRecycleAdapter);

        // TODO: 5 Check for interent connection (display message if not available + AndroidManifest exception)
        // TODO: 6 Add option to choose most popular OR highest rated - menu
        //URL url = NetworkRequest.buildUrl("popular");
        new multiThreadingClass().execute("popular");
    }

    @Override
    public void onClick(String title, String releaseDate, String posterUrl, Double average, String overview) {
        Intent intentToStartDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
        // TODO: 2 Send Parcel Object
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, title);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, releaseDate);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, posterUrl);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, average);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, overview);
        startActivity(intentToStartDetailActivity);
    }

    public class multiThreadingClass extends AsyncTask<String, Void, ArrayList<MovieComponent>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<MovieComponent> doInBackground(String... strings) {

            if (strings.length == 0) {
                return null;
            }

            String selection = strings[0];
            URL url = NetworkRequest.buildUrl(selection);

            try {
                String jsonMovieResponse = NetworkRequest.makeHttpRequest(url);
                mAllDataInArrayList = JsonParse.extractNews(jsonMovieResponse);
            } catch (IOException e) {
                Log.e("MainActivity", "Error in MA with makeHttpRequest", e);
            }
            //mPostersUrlString = prepareData();
            return mAllDataInArrayList;
        }
        @Override
        protected void onPostExecute(ArrayList<MovieComponent> movieComponents) {
            if (!movieComponents.isEmpty()) {

                mMovieRecycleAdapter.replaceMovieArrayList(movieComponents);
            }
        }
    }
    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("mAllDataInArrayList",mAllDataInArrayList);
        // TODO: 1 IMPLEMENT Parcelable to MovieComponent Class

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

//    Why Parcelable ?
//      When starting on Android, we all learn that we cannot just pass object references to activities and fragments,
//      we have to put those in an Intent / Bundle.
//
//      We have two options, we can either make our objects Parcelable or Serializable.
//      Serializable mechanism == the Master of Simplicity in implementing;
//      Parcelable mechanism == the Speed King;
}


