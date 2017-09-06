package com.example.wojtekkurylo.moviesapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MovieRecyclerAdapter mMovieRecycleAdapter;

    // TEST TEST TEST
    private final String android_image_urls[] = {
            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
            "https://api.learn2crack.com/android/images/honey.png",
            "https://api.learn2crack.com/android/images/icecream.png",
            "https://api.learn2crack.com/android/images/jellybean.png",
            "https://api.learn2crack.com/android/images/kitkat.png",
            "https://api.learn2crack.com/android/images/lollipop.png",
            "https://api.learn2crack.com/android/images/marshmallow.png"
    };
    // TEST TEST TEST

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //URL url = NetworkRequest.buildUrl("popular");

        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MovieComponent> postersUrlString = prepareData();
        // TODO: 06.09.2017 I need to replace postersUrlString with ArrayList<MovieComponent> from onPostExecute method
        mMovieRecycleAdapter = new MovieRecyclerAdapter(getApplicationContext(), postersUrlString);
        recyclerView.setAdapter(mMovieRecycleAdapter);
    }

    private ArrayList<MovieComponent> prepareData() {
        ArrayList<MovieComponent> urlList = new ArrayList<>();
        for (int i = 0; i < android_image_urls.length; i++) {
            MovieComponent urlString = new MovieComponent();
            urlString.setMovieImageUrl(android_image_urls[i]);
            urlList.add(urlString);
        }
        Log.d("MainActivity", "ArrayLista Stringow: " + urlList.toString());
        return urlList;
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
                // TODO: 06.09.2017 HERE I will implement method which return ArrayList<MovieComponent> from String jsonMovieResponse
            } catch (IOException e) {
                Log.e("MainActivity", "Error in MA with makeHttpRequest", e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(ArrayList<MovieComponent> movieComponents) {
            if (!movieComponents.isEmpty()) {
                // add the List at the last position of mNewsCustomAdapter constructor
                mMovieRecycleAdapter.addAll(movieComponents);
                // TODO: 06.09.2017  HOW to update Adapter with ArrayList from JSON ?
            }

        }
    }
}
