package com.example.wojtekkurylo.moviesapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wojtekkurylo.moviesapp.Model.MovieComponent;
import com.example.wojtekkurylo.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * {@link MovieRecyclerAdapter} exposes a grid of movie to a MainActivity
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<MovieComponent> mAllDataInArrayList;
    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final MovieAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface MovieAdapterOnClickHandler {
        void onClick(String title, String releaseDate, String posterUrl, Double average, String overview);
    }


    /**
     * Creates a MovieRecyclerAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     * @param context       The context.
     */
    public MovieRecyclerAdapter(MovieAdapterOnClickHandler clickHandler, Context context) {
        mContext = context;
        mClickHandler = clickHandler;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent The ViewGroup that these ViewHolders are contained within.
     * @param viewType  viewType integer to provide a different layout.
     *
     * @return A new MovieRecyclerAdapter.MovieViewHolder that holds the View for each grid item
     */
    @Override
    public MovieRecyclerAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);
        return new MovieViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the movie
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder     The ViewHolder which should be updated to represent the
     *                   contents of the item at the given position in the data set.
     * @param position   The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(mAllDataInArrayList.get(position).getPosterUrl())
                .into(holder.itemImage);
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our movie
     */
    @Override
    public int getItemCount() {
        if (mAllDataInArrayList == null) {
            return 0;
        } else {
            return mAllDataInArrayList.size();
        }
    }

    /**
     * Cache of the children views for a movie grid item.
     */
    public class MovieViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public final ImageView itemImage;

        public MovieViewHolder(View viewItem) {
            super(viewItem);
            itemImage = viewItem.findViewById(R.id.item_image);
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
            mClickHandler.onClick(title, releaseDate,posterUrl,average, overview);
        }

    }

    /**
     * This method is used to set the movie on a mMovieRecycleAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new mMovieRecycleAdapter to display it.
     *
     * @param movieArrayList The new movie data to be displayed.
     */
    public void replaceMovieArrayList(ArrayList<MovieComponent> movieArrayList) {
        mAllDataInArrayList = movieArrayList;
        notifyDataSetChanged();
    }
}




