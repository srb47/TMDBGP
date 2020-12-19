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
import com.telenorgp.tmdbgp.Database.FavouriteEntity;
import com.telenorgp.tmdbgp.R;
import com.telenorgp.tmdbgp.Utils.CONSTANTS;

import java.util.ArrayList;


public class VerticalFavouriteCustomAdapter extends RecyclerView.Adapter<VerticalFavouriteCustomAdapter.FavouriteViewHolder> {
    private ArrayList<FavouriteEntity> mFavEntities;
    private Context mContext;

    public VerticalFavouriteCustomAdapter(Context context, ArrayList<FavouriteEntity> favouriteEntities) {
        mContext = context;
        mFavEntities = favouriteEntities;
    }

    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.favourite_card_layout, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        FavouriteEntity favouriteEntity = mFavEntities.get(position);
        holder.mTitle.setText(favouriteEntity.getName());
//      holder.mRating.setText(String.valueOf(mtvItem.getVoteAverage()));

        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + favouriteEntity.getPosterPath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mOverview.setText(favouriteEntity.getOverview());
        holder.mReleaseDate.setText(favouriteEntity.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return mFavEntities.size();
    }

    static class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle;
        // TextView mRating;
        TextView mOverview;
        TextView mReleaseDate;
        View mItemView;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.favourite_vertical_card_thumbnail);
            mTitle = itemView.findViewById(R.id.favourite_vertical_card_title);
            mOverview = itemView.findViewById(R.id.favourite_vertical_card_overview);
            mReleaseDate = itemView.findViewById(R.id.favourite_vertical_card_release);
        }

        @Override
        public void onClick(View view) {
            Log.i("Tv Item Click", "true");
        }
    }
}