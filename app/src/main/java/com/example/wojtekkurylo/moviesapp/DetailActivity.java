package com.example.wojtekkurylo.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(DetailActivity.this);



    }
}
