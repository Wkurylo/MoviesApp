package com.example.wojtekkurylo.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

        MovieComponent receivedMovieObject = (MovieComponent) getIntent().getParcelableExtra("movieObject");

        mDetailTitle.setText(receivedMovieObject.getTitle());
        mDetailRelease.setText(receivedMovieObject.getReleaseDate());

        Picasso.with(getApplicationContext())
                .load(receivedMovieObject.getPosterUrlDetailActivity())
                .into(mDetailPoster);
        Log.d("DetailActivity", "PosterUrl: " + receivedMovieObject.getPosterUrlDetailActivity());

        mDetailAverage.setText(receivedMovieObject.getAverage().toString());
        mDetailOverview.setText(receivedMovieObject.getPlotSynopsis());





    }
}
