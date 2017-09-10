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
    private ArrayList<MovieComponent> mAllDataInArrayList;
    private RecyclerView mRecyclerView;

//    // TEST TEST TEST
//    private final String android_image_urls[] = {
//            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
//            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
//            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
//            "https://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg",
//            "https://api.learn2crack.com/android/images/honey.png",
//            "https://api.learn2crack.com/android/images/icecream.png",
//            "https://api.learn2crack.com/android/images/jellybean.png",
//            "https://api.learn2crack.com/android/images/kitkat.png",
//            "https://api.learn2crack.com/android/images/lollipop.png",
//            "https://api.learn2crack.com/android/images/marshmallow.png"
//    };
//    // TEST TEST TEST

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        //mPostersUrlString = prepareData();
        mMovieRecycleAdapter = new MovieRecyclerAdapter(getApplicationContext(), mAllDataInArrayList);
        mRecyclerView.setAdapter(mMovieRecycleAdapter);

        //URL url = NetworkRequest.buildUrl("popular");
        new multiThreadingClass().execute("popular");
    }

//    private ArrayList<MovieComponent> prepareData() {
//        ArrayList<MovieComponent> urlList = new ArrayList<>();
//        for (int i = 0; i < android_image_urls.length; i++) {
//            MovieComponent urlString = new MovieComponent(null,null,null,null,null);
//            urlString.setMovieImageUrl(android_image_urls[i]);
//            urlList.add(urlString);
//        }
//        Log.d("MainActivity", "TO SPRAWDZ: ArrayLista Stringow: " + urlList.toString());
//        return urlList;
//    }

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
                ArrayList<MovieComponent> allDataInArrayList = JsonParse.extractNews(jsonMovieResponse);
                Log.d("Main Activity", "TO SPRAWDZ: What I received from JSON: " +allDataInArrayList);
            } catch (IOException e) {
                Log.e("MainActivity", "Error in MA with makeHttpRequest", e);
            }
            //mPostersUrlString = prepareData();
            return mAllDataInArrayList;
        }


        @Override
        protected void onPostExecute(ArrayList<MovieComponent> movieComponents) {
            if (!movieComponents.isEmpty()) {
                Log.d("MainActivity", "ArrayList<MovieComponent>: " + movieComponents);

                mMovieRecycleAdapter.replaceMovieArrayList(movieComponents);
            }

        }
    }
}
