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


public class HorizontalMovieCustomAdapter extends RecyclerView.Adapter<HorizontalMovieCustomAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies;
    private Context mContext;
    private onClickCustomListener mOnItemClickListener;

    public HorizontalMovieCustomAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
        try {
            mOnItemClickListener = (onClickCustomListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickedListener");
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.movie_card_layout, parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(inflatedView);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movieViewHolder.id != null && movieViewHolder.id != "") {
                    mOnItemClickListener.onItemClick(movieViewHolder.id, "movie");
                }
            }
        });

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie mMovie = mMovies.get(position);
        holder.mTitle.setText(mMovie.getTitle());
        holder.mReleaseDate.setText(mMovie.getReleaseDate());
        holder.mRating.setText(String.valueOf(mMovie.getVoteAverage()));
        holder.id = String.valueOf(mMovie.getId());
        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + mMovie.getPosterPath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public interface onClickCustomListener {
        void onItemClick(String id, String type);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        TextView mReleaseDate;
        TextView mRating;
        View mItemView;
        String id;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            id = "";
            mImageView = itemView.findViewById(R.id.movie_card_thumbnail);
            mTitle = itemView.findViewById(R.id.movie_card_name);
            mReleaseDate = itemView.findViewById(R.id.release_date_card);
            mRating = itemView.findViewById(R.id.movie_card_rating);
        }

        @Override
        public void onClick(View view) {
            Log.i("Movie Item Click", "true");
        }
    }
}
