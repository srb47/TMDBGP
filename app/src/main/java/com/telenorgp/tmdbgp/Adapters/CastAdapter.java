package com.telenorgp.tmdbgp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.telenorgp.tmdbgp.Entities.Cast;
import com.telenorgp.tmdbgp.R;
import com.telenorgp.tmdbgp.Utils.CONSTANTS;

import java.util.ArrayList;


public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private ArrayList<Cast> mCast;
    private Context mContext;

    public CastAdapter(Context context, ArrayList<Cast> casts) {
        mCast = casts;
        mContext = context;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.cast, parent, false);
        return new CastViewHolder(inflatedView);
    }

    Integer errorHandlerForCastImage(CastViewHolder holder) {
        Picasso.with(mContext).load(R.raw.default_profile_pic).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        return null;
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        holder.mProgressBar.setVisibility(View.VISIBLE);
        holder.mNameTextView.setText(mCast.get(position).getName());
        holder.mCharacterTextView.setText(mCast.get(position).getCharacter());
        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                errorHandlerForCastImage(holder);
            }
        });

        builder.build().load(CONSTANTS.BASE_POSTER_URL_SMALL + mCast.get(position).getProfilePath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);
        holder.mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mCast.size();
    }

    static class CastViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mNameTextView;
        TextView mCharacterTextView;
        View mItemView;
        View mProgressBar;

        public CastViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.cast_image);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
            mNameTextView = itemView.findViewById(R.id.cast_name);
            mCharacterTextView = itemView.findViewById(R.id.cast_character);
        }
    }
}
