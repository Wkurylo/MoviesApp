package com.example.wojtekkurylo.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wojtekkurylo on 02.09.2017.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<MovieComponent> mPostersUrlList;


    public MovieRecyclerAdapter(Context context, ArrayList<MovieComponent> postersUrlList) {
        mContext = context;
        mPostersUrlList = postersUrlList;
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
                .load(mPostersUrlList.get(position).getPosterUrl())
                //.resize(120, 60).
                .placeholder(R.color.colorAccent)
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return mPostersUrlList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;

        public MovieViewHolder(View viewItem) {
            super(viewItem);
            itemImage = (ImageView) viewItem.findViewById(R.id.item_image);
        }
    }
}




