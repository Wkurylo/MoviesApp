package com.example.wojtekkurylo.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieRecyclerAdapter.MovieAdapterOnClickHandler, AdapterView.OnItemSelectedListener {

    private MovieRecyclerAdapter mMovieRecycleAdapter = new MovieRecyclerAdapter(this, MainActivity.this);
    private ArrayList<MovieComponent> mAllDataInArrayList;
    private RecyclerView mRecyclerView;
    private String mSearchString;

    //@BindView == ListView newsListView = (ListView) - automatically casting
    @BindView(R.id.loading_spinner)
    ProgressBar spinnerView;
    @BindView(R.id.no_internet)
    TextView noInternet;
    @BindView(R.id.no_movies)
    TextView noMovies;

    // TODO: 14.09.2017 Remove additional Logs
    // TODO: 14.09.2017 Add code description

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // recovering the instance state if exist
        if (savedInstanceState == null || !savedInstanceState.containsKey("mAllDataInArrayList")) {
            mAllDataInArrayList = new ArrayList<MovieComponent>();
        } else {
            mAllDataInArrayList = savedInstanceState.getParcelableArrayList("mAllDataInArrayList");
            //mMovieRecycleAdapter.replaceMovieArrayList(mAllDataInArrayList);
        }
        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_main.xml file
        setContentView(R.layout.activity_main);

        // ButterKnife == findViewById() - performing the action for @BindViews
        ButterKnife.bind(MainActivity.this);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(mMovieRecycleAdapter);

        // Checking the internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        // set the text to display in stead of ListView while no internet
        noInternet.setText(R.string.no_internet);

        if (isConnected) {
            noInternet.setVisibility(View.GONE);
        } else {
            mRecyclerView.clearAnimation();
            noInternet.setVisibility(View.VISIBLE);
            spinnerView.setVisibility(View.GONE);
            noMovies.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(String title, String releaseDate, String posterUrl, Double average, String overview) {
        Intent intentToStartDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
        MovieComponent movieObject = new MovieComponent(title, releaseDate, posterUrl, average, overview);
        intentToStartDetailActivity.putExtra("movieObject", movieObject);
        startActivity(intentToStartDetailActivity);
    }

    public class multiThreadingClass extends AsyncTask<String, Void, ArrayList<MovieComponent>> {
        @Override
        protected void onPreExecute() {
            if (spinnerView.getVisibility() == View.GONE) {
                spinnerView.setVisibility(View.VISIBLE);
            }
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
            if (spinnerView.getVisibility() == View.VISIBLE) {
                spinnerView.setVisibility(View.GONE);
            }
            if (!movieComponents.isEmpty()) {
                mMovieRecycleAdapter.replaceMovieArrayList(movieComponents);
                noInternet.setVisibility(View.GONE);
            } else {
                noMovies.setText(R.string.no_movies);
            }
        }
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("mAllDataInArrayList", mAllDataInArrayList);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spinner_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) item.getActionView();
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add(getString(R.string.popular));
        categories.add(getString(R.string.top_rated));

        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSearchString = adapterView.getItemAtPosition(i).toString();
        Log.d("MainActivity", "SELECTED onItemSelected: " + mSearchString);
        new multiThreadingClass().execute(mSearchString);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }


    //    Why Parcelable ?
//      When starting on Android, we all learn that we cannot just pass object references to activities and fragments,
//      we have to put those in an Intent / Bundle.
//
//      We have two options, we can either make our objects Parcelable or Serializable.
//      Serializable mechanism == the Master of Simplicity in implementing;
//      Parcelable mechanism == the Speed King;
}


