package com.example.wojtekkurylo.moviesapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wojtekkurylo.moviesapp.MovieComponent;
import com.example.wojtekkurylo.moviesapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_title)
    TextView mDetailTitle;
    @BindView(R.id.detail_image)
    ImageView mDetailPoster;
    @BindView(R.id.detail_release_date)
    TextView mDetailRelease;
    @BindView(R.id.detail_average)
    TextView mDetailAverage;
    @BindView(R.id.detail_overview)
    TextView mDetailOverview;

    private String mTitle;
    private String mReleaseDate;
    private String mPosterUrl;
    private Double mAverage;
    private String mPlotSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(DetailActivity.this);

        MovieComponent receivedMovieObject = getIntent().getParcelableExtra("movieObject");

        mDetailTitle.setText(receivedMovieObject.getTitle());
        mDetailRelease.setText(getString(R.string.release_date) +" "+ receivedMovieObject.getReleaseDate());

        Picasso.with(getApplicationContext())
                .load(receivedMovieObject.getPosterUrlDetailActivity())
                .error(R.drawable.noimage)
                .into(mDetailPoster);
        Log.d("DetailActivity", "PosterUrl: " + receivedMovieObject.getPosterUrlDetailActivity());

        mDetailAverage.setText(getString(R.string.average) +" "+receivedMovieObject.getAverage().toString());
        mDetailOverview.setText(getString(R.string.overview) + receivedMovieObject.getPlotSynopsis());





    }
}
