package com.example.wojtekkurylo.moviesapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.example.wojtekkurylo.moviesapp.BuildConfig;
import com.example.wojtekkurylo.moviesapp.Model.MovieComponent;
import com.example.wojtekkurylo.moviesapp.Adapter.MovieRecyclerAdapter;
import com.example.wojtekkurylo.moviesapp.R;
import com.example.wojtekkurylo.moviesapp.Rest.MovieApiService;
import com.example.wojtekkurylo.moviesapp.Values.Constants;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieRecyclerAdapter.MovieAdapterOnClickHandler, AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MovieRecyclerAdapter mMovieRecycleAdapter = new MovieRecyclerAdapter(this, MainActivity.this);
    private ArrayList<MovieComponent> mAllDataInArrayList;
    private RecyclerView mRecyclerView;
    private String mSearchString;
    private String mSelectedString;
    private static Retrofit mRetrofit = null;
    private Call<MovieComponent> mCall;
    private MovieApiService mMovieApiService;
    private boolean mSavedInstanceChecker = false;

    // themoviedb.org API KEY
    private static final String API_KEY_VALUE = BuildConfig.API_KEY;
    // base URL
    private static final String MOVIE_DB = "https://api.themoviedb.org/3/";


    //@BindView == ListView newsListView = (ListView) - automatically casting
    @BindView(R.id.loading_spinner)
    ProgressBar spinnerView;
    @BindView(R.id.no_internet)
    TextView noInternet;
    @BindView(R.id.no_movies)
    TextView noMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // recovering the instance state if exist
        if (savedInstanceState == null || !savedInstanceState.containsKey(Constants.KEY_ARRAY_LIST_PARCELABLE)) {
            mAllDataInArrayList = new ArrayList<>();
            mSavedInstanceChecker = false;
        } else {
            mAllDataInArrayList = savedInstanceState.getParcelableArrayList("mAllDataInArrayList");
            mSearchString = savedInstanceState.getString(Constants.KEY_STRING_MENU_SELECTION);
            mSavedInstanceChecker = true;
        }
        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_main.xml file
        setContentView(R.layout.activity_main);

        // ButterKnife == findViewById() - performing the action for @BindViews
        ButterKnife.bind(MainActivity.this);

         /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recycler_view);
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView.setHasFixedSize(true);
        /*
         * GridLayoutManager with 2 columns
         */
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView.setAdapter(mMovieRecycleAdapter);

        // Checking the internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        // set the text to display in stead of ListView while no internet
        noInternet.setText(R.string.no_internet);

        // Checking the internet connection
        if (isConnected) {
            noInternet.setVisibility(View.GONE);
        } else {
            mRecyclerView.clearAnimation();
            noInternet.setVisibility(View.VISIBLE);
            spinnerView.setVisibility(View.GONE);
            noMovies.setVisibility(View.GONE);
        }
    }

    /**
     * This method is overridden by our MainActivity class in order to handle RecyclerView item
     * clicks.
     *
     * @param title       The title for the movie that was clicked
     * @param releaseDate The releaseDate for the movie that was clicked
     * @param posterUrl   The Image URL in String for the movie that was clicked
     * @param average     The average for the movie that was clicked
     * @param overview    The overview for the movie that was clicked
     */
    @Override
    public void onClick(String title, String releaseDate, String posterUrl, Double average, String overview) {
        Intent intentToStartDetailActivity = new Intent(MainActivity.this, DetailActivity.class);
        MovieComponent movieObject = new MovieComponent(title, releaseDate, posterUrl, average, overview);
        intentToStartDetailActivity.putExtra("movieObject", movieObject);
        startActivity(intentToStartDetailActivity);
    }

    // This method create an instance of Retrofit
    public void useRetrofilToConnectAndGetAiData() {
        if (spinnerView.getVisibility() == View.GONE) {
            spinnerView.setVisibility(View.VISIBLE);
        }

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(MOVIE_DB)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        mMovieApiService = mRetrofit.create(MovieApiService.class);

        mCall = mMovieApiService.getSelectedMovies(mSearchString,API_KEY_VALUE);

        mCall.enqueue(new Callback<MovieComponent>() {
            @Override
            public void onResponse(Call<MovieComponent> call, Response<MovieComponent> response) {

                mAllDataInArrayList = response.body().getResults();

                Log.d(TAG,"Yes I am making another HTTP request + parse" + mAllDataInArrayList);
                if (spinnerView.getVisibility() == View.VISIBLE) {
                    spinnerView.setVisibility(View.GONE);
                }
                noInternet.setVisibility(View.GONE);
                mMovieRecycleAdapter.replaceMovieArrayList(mAllDataInArrayList);

            }

            @Override
            public void onFailure(Call<MovieComponent> call, Throwable t) {
                noMovies.setText(R.string.no_movies);
                Log.e(TAG, t.toString());
            }
        });

    }

    // We restore after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    // The system calls onRestoreInstanceState() only if there is a saved state to restore,
    // so you do not need to check whether the Bundle is null
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // recovering the instance state if exist
        mAllDataInArrayList = savedInstanceState.getParcelableArrayList("mAllDataInArrayList");
        mSearchString = savedInstanceState.getString(Constants.KEY_STRING_MENU_SELECTION);
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Constants.KEY_ARRAY_LIST_PARCELABLE, mAllDataInArrayList);
        outState.putString(Constants.KEY_STRING_MENU_SELECTION,mSearchString);
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
        List<String> categories = new ArrayList<>();
        categories.add(getString(R.string.popular));
        categories.add(getString(R.string.top_rated));

        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(adapter);

        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // If the mSearchString has been received in onSaveInstance then :
        if(mSavedInstanceChecker){
            mSelectedString = mSearchString;
        } else {
            mSelectedString = adapterView.getSelectedItem().toString();
        }
        if(mAllDataInArrayList.isEmpty() || !mSelectedString.equals(mSearchString)) {
            mSearchString = mSelectedString;
            useRetrofilToConnectAndGetAiData();
        } else {
            if (spinnerView.getVisibility() == View.VISIBLE) {
                spinnerView.setVisibility(View.GONE);
            }
            noInternet.setVisibility(View.GONE);
            mSavedInstanceChecker = false;
            mMovieRecycleAdapter.replaceMovieArrayList(mAllDataInArrayList);
        }

        // TODO: Will be used later
//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        String defaultValue = getResources().getString(R.string.popular);
//        String sharedString = sharedPref.getString(getString(R.string.selected),defaultValue);
//        if(sharedString.equals(mSearchString))
//        {
//            return;
//        } else {
//             /* Sending the HTTP and JSON parse in to background */
//            new multiThreadingClass().execute(mSearchString);
//            Log.d("MainActivity", "SELECTED onItemSelected: " + mSearchString);
//        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }

}


