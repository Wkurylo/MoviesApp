package com.example.wojtekkurylo.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = BuildConfig.API_KEY;

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

        initViews();
    }

    private void initViews()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MovieComponent> postersUrlString = prepareData();
        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(getApplicationContext(),postersUrlString);
        recyclerView.setAdapter(adapter);
    }
    private ArrayList<MovieComponent> prepareData()
    {
        ArrayList<MovieComponent> urlList = new ArrayList<>();
        for(int i=0;i<android_image_urls.length;i++){
            MovieComponent urlString = new MovieComponent();
            urlString.setMovieImageUrl(android_image_urls[i]);
            urlList.add(urlString);
        }
        Log.d("MainActivity", "ArrayLista Stringow: " + urlList.toString());
        return urlList;
    }
}
