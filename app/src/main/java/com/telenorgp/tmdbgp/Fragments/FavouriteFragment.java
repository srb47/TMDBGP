package com.telenorgp.tmdbgp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.telenorgp.tmdbgp.Adapters.VerticalFavouriteCustomAdapter;
import com.telenorgp.tmdbgp.Database.FavouriteDatabase;
import com.telenorgp.tmdbgp.Database.FavouriteEntity;
import com.telenorgp.tmdbgp.R;
import com.telenorgp.tmdbgp.Utils.CONSTANTS;
import com.telenorgp.tmdbgp.Utils.CONTRACT;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavouriteFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private ArrayList<FavouriteEntity> mFavouriteEntities;

    private Retrofit mBuilder;
    private boolean loading  = false;
    private FavouriteDatabase db;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setupFavourites(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
//        mProgressBar = view.findViewById(R.id._progress_bar);
        mBuilder = new Retrofit.Builder()
                .baseUrl(CONSTANTS.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRecyclerView = view.findViewById(R.id.favourite_vertical_recycler_view);

        db = Room.databaseBuilder(getContext(), FavouriteDatabase.class, CONTRACT.FAVOURTIES_DBNAME).allowMainThreadQueries().build();
        mFavouriteEntities = new ArrayList<>(db.favouriteEntityDao().getAll());
        VerticalFavouriteCustomAdapter mVerticalFavouriteCustomAdapter = new VerticalFavouriteCustomAdapter(getContext(), mFavouriteEntities);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        verticalLayoutManager.canScrollVertically();

        mRecyclerView.setLayoutManager(verticalLayoutManager);
        mRecyclerView.setAdapter(mVerticalFavouriteCustomAdapter);
        mVerticalFavouriteCustomAdapter.notifyDataSetChanged();
        return view;
    }
}
