package com.example.wojtekkurylo.moviesapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wojtekkurylo on 02.09.2017.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<MovieComponent> mAllDataInArrayList;
    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(String title, String releaseDate, String posterUrl, Double average, String overview);
    }


    public MovieRecyclerAdapter(MovieAdapterOnClickHandler clickHandler, Context context) {
        mContext = context;
        //mPostersUrlList = postersUrlList;
        mClickHandler = clickHandler;
    }

    @Override
    public MovieRecyclerAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // Jezeli z ArrayList to braz URL z Stringow w kolejnosci np: load(movie_image.get(i).getAndroid_image_url()
        Picasso.with(mContext)
                .load(mAllDataInArrayList.get(position).getPosterUrl())
                //.resize(120, 60).
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        if (mAllDataInArrayList == null) {
            return 0;
        } else {
            return mAllDataInArrayList.size();
        }
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final ImageView itemImage;

        public MovieViewHolder(View viewItem) {
            super(viewItem);
            itemImage = (ImageView) viewItem.findViewById(R.id.item_image);
            viewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();
            MovieComponent movie = mAllDataInArrayList.get(adapterPosition);
            String title = movie.getTitle();
            String releaseDate = movie.getReleaseDate();
            String posterUrl = movie.getPosterUrl();
            Double average = movie.getAverage();
            String overview = movie.getPlotSynopsis();
            Log.d("Movie Recycle Adapter", "Result: " + title + releaseDate + posterUrl + average + overview);
            mClickHandler.onClick(title, releaseDate,posterUrl,average, overview);
        }

    }

    public void replaceMovieArrayList(ArrayList<MovieComponent> movieArrayList) {
        //mPostersUrlList.clear();
        mAllDataInArrayList = movieArrayList;
        //mPostersUrlList.addAll(movieArrayList);
        notifyDataSetChanged();
    }
}




