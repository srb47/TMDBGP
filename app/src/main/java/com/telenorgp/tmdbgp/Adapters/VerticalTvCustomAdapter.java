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
import com.telenorgp.tmdbgp.Entities.TvShow;
import com.telenorgp.tmdbgp.R;
import com.telenorgp.tmdbgp.Utils.CONSTANTS;

import java.util.ArrayList;




public class VerticalTvCustomAdapter extends RecyclerView.Adapter<VerticalTvCustomAdapter.TvViewHolder> {
    private ArrayList<TvShow> mTvShows;
    private Context mContext;

    public VerticalTvCustomAdapter(Context context, ArrayList<TvShow> tvShows) {
        mContext = context;
        mTvShows = tvShows;
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_vertical_card_layout, parent, false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
        TvShow mTv = mTvShows.get(position);
        holder.mTitle.setText(mTv.getName());

        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + mTv.getPosterPath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mOverview.setText(mTv.getOverview());
        holder.mReleaseDate.setText(mTv.getFirstAirDate());
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    static class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        // TextView mRating;
        TextView mOverview;
        TextView mReleaseDate;
        View mItemView;

        public TvViewHolder(View itemView) {
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