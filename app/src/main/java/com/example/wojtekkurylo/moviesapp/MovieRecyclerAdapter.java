package com.example.wojtekkurylo.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by wojtekkurylo on 02.09.2017.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    private Context mContext;
    private String mUrlMovieImage;
    private int mCounter = 0;

    // Jezeli z JSON bede brac ArrayListe z URL do Image to zmienic jako parametr dla konstruktora na ArrayList
    public MovieRecyclerAdapter(Context context, String urlMovieImage) {
        mContext = context;
        mUrlMovieImage = urlMovieImage;
        mCounter = mCounter +1;
        Log.d("Called MRAdapter","mCounter status: " + mCounter);
    }

    @Override
    public MovieRecyclerAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieRecyclerAdapter.MovieViewHolder holder, int position) {
        // Jezeli z ArrayList to braz URL z Stringow w kolejnosci np: load(movie_image.get(i).getAndroid_image_url()
        Picasso.with(mContext).load(mUrlMovieImage).resize(120, 120).into(holder.imageMovie);
    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount","mCounter status: " + mCounter);
        return mCounter;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageMovie;

        public MovieViewHolder(View viewItem) {
            super(viewItem);
            imageMovie = (ImageView) viewItem.findViewById(R.id.item_image);
        }
    }
}




