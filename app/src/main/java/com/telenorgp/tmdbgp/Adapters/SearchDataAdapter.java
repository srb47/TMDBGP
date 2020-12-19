package com.telenorgp.tmdbgp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.telenorgp.tmdbgp.Entities.SearchItem;
import com.telenorgp.tmdbgp.R;
import com.telenorgp.tmdbgp.Utils.CONSTANTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.SearchViewHolder> {
    private ArrayList<SearchItem> mSearchItems;
    private Context mContext;
    private onClickCustomListener mOnItemClickListener;

    public SearchDataAdapter(Context context, ArrayList<SearchItem> searchItems) {
        mSearchItems = searchItems;
        Collections.sort(mSearchItems, new Comparator<SearchItem>() {
            @Override
            public int compare(SearchItem searchItem, SearchItem t1) {
                return ((int) Math.signum(t1.getPopularity() - searchItem.getPopularity()));
            }
        });

        mContext = context;
        try {
            mOnItemClickListener = (onClickCustomListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnItemClickedListener");
        }
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(mContext).inflate(R.layout.search_item_card_layout, parent, false);
        final SearchViewHolder searchViewHolder = new SearchViewHolder(inflatedView);
        searchViewHolder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(searchViewHolder.mId, searchViewHolder.mMediaType);
            }
        });

        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Picasso.with(mContext).load(CONSTANTS.BASE_POSTER_URL_SMALL + mSearchItems.get(position).getPosterPath()).resize(CONSTANTS.BASE_POSTER_WIDTH_SMALL, CONSTANTS.BASE_POSTER_HEIGHT_SMALL).into(holder.mImageView);

        SearchItem searchItem = mSearchItems.get(position);
        // hack/fix if title is null
        holder.mTitle.setText(searchItem.getTitle() == null ? searchItem.getName() : searchItem.getTitle());
        holder.mRelease.setText(searchItem.getReleaseDate());
        holder.mId = searchItem.getId().toString();
        holder.mMediaType = searchItem.getMediaType();
    }

    @Override
    public int getItemCount() {
        return mSearchItems.size();
    }

    public interface onClickCustomListener {
        void onItemClick(String id, String type);
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mRelease;
        View mItemView;
        String mId;
        String mMediaType;

        public SearchViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mImageView = itemView.findViewById(R.id.search_card_thumbnail);
            mTitle = itemView.findViewById(R.id.search_card_title);
            mRelease = itemView.findViewById(R.id.search_card_release);
            mId = "";
            mMediaType = "";
        }
    }
}