package com.telenorgp.tmdbgp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.telenorgp.tmdbgp.Entities.Movie;
import com.telenorgp.tmdbgp.R;
import com.telenorgp.tmdbgp.Utils.CONSTANTS;

import java.util.ArrayList;

//import com.bumptech.glide.Glide;

public class VerticalMovieCustomAdapter extends RecyclerView.Adapter<VerticalMovieCustomAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies;
    private Context mContext;

    public VerticalMovieCustomAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_vertical_card_layout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie mMovie = mMovies.get(position);
        holder.mTitle.setText(mMovie.getTitle());

        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + mMovie.getPosterPath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mOverview.setText(mMovie.getOverview());
        holder.mReleaseDate.setText(mMovie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        // TextView mRating;
        TextView mOverview;
        TextView mReleaseDate;
        View mItemView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.movie_vertical_card_thumbnail);
            mTitle = itemView.findViewById(R.id.movie_vertical_card_title);
            mOverview = itemView.findViewById(R.id.movie_vertical_card_overview);
            mReleaseDate = itemView.findViewById(R.id.movie_vertical_card_release);
        }

        @Override
        public void onClick(View view) {
            Log.i("Tv Item Click", "true");
        }
    }
}