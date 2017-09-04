package com.example.wojtekkurylo.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;

    // In mPostersUrlList we keep JSON response with URL's in Strings
    ArrayList<MovieComponent> mPostersUrlList = new ArrayList<MovieComponent>();

    // TEST TEST TEST
    private final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
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
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_numbers);
        mRecyclerView.hasFixedSize();
        mGridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        ArrayList postersUrlString = prepareData();
        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(getApplicationContext(),postersUrlString);
        mRecyclerView.setAdapter(adapter);
    }
    private ArrayList prepareData()
    {
        ArrayList urlList = new ArrayList<>();
        for(int i=0;i<android_image_urls.length;i++){
            MovieComponent urlString = new MovieComponent();
            urlString.setMovieImageUrl(android_image_urls[i]);
            urlList.add(urlString);
        }
        Log.d("MainActivity", "ArrayLista Stringow: " + urlList.toString());
        return urlList;
    }
}
