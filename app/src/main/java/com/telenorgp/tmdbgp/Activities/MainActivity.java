package com.telenorgp.tmdbgp.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.telenorgp.tmdbgp.Adapters.HorizontalMovieCustomAdapter;
import com.telenorgp.tmdbgp.Adapters.HorizontalTvCustomAdapter;
import com.telenorgp.tmdbgp.Fragments.FavouriteFragment;
import com.telenorgp.tmdbgp.Fragments.MovieFragment;
import com.telenorgp.tmdbgp.Fragments.TvFragment;
import com.telenorgp.tmdbgp.Network.NetworkChangeReceiver;
import com.telenorgp.tmdbgp.R;
import com.telenorgp.tmdbgp.Utils.CONSTANTS;

public class MainActivity extends AppCompatActivity implements HorizontalMovieCustomAdapter.onClickCustomListener, HorizontalTvCustomAdapter.onClickCustomListener, SearchView.OnQueryTextListener, View.OnClickListener {

    private static TextView mCheckConnection;
    private NetworkChangeReceiver mNetworkChangeReceiver;
    private BottomNavigationView mBottomNavigationView;
    private MenuItem mSearchMenu;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem tab) {
            Fragment fragment = null;
            switch (tab.getItemId()) {
                case R.id.menu_movies:
                MovieFragment fragment1 = new MovieFragment();
                FragmentTransaction fragmentTransaction1 =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.frame_content, fragment1, "");
                fragmentTransaction1.commit();
                return true;

                case R.id.menu_tv_shows:
                    TvFragment fragment2 = new TvFragment();
                    FragmentTransaction fragmentTransaction2 =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame_content, fragment2, "");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.menu_personal_favourites:
                    FavouriteFragment fragment3 = new FavouriteFragment();
                    FragmentTransaction fragmentTransaction3 =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.frame_content, fragment3, "");
                    fragmentTransaction3.commit();
                    return true;
            }
            return loadFragment(fragment);
        }


    };

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commit();
            return true;
        }
        return false;
    }



    public static void dialog(boolean value) {
        if (value) {
            mCheckConnection.setText("TMDBGP is back !!!");
            mCheckConnection.setBackgroundColor(Color.DKGRAY);
            mCheckConnection.setTextColor(Color.WHITE);

            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    mCheckConnection.setVisibility(View.GONE);
                }
            };
            handler.postDelayed(delayrunnable, 3000);
        } else {
            mCheckConnection.setVisibility(View.VISIBLE);
            mCheckConnection.setText("Could not Connect to internet");
            mCheckConnection.setBackgroundColor(Color.RED);
            mCheckConnection.setTextColor(Color.WHITE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new MovieFragment());

        mBottomNavigationView = findViewById(R.id.navigation);
        mCheckConnection = findViewById(R.id.check_connection);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNetworkChangeReceiver = new NetworkChangeReceiver();

        registerNetworkBroadcastForNougat();
    }

    private void registerNetworkBroadcastForNougat() {
        registerReceiver(mNetworkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkChangeReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        mSearchMenu = menu.findItem(R.id.search);
        ((SearchView) mSearchMenu.getActionView()).setOnQueryTextListener(MainActivity.this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {

        Snackbar.make(findViewById(R.id.frame_content), "Do you want to exit?", Snackbar.LENGTH_SHORT).setAction("Exit", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.super.onBackPressed();
            }
        }).show();

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        mSearchMenu.collapseActionView();
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra(SearchManager.QUERY, s);
        startActivity(intent);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }

    @Override
    public void onItemClick(String id, String type) {
        Intent intent;
        switch (type) {
            case "tv":
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                startActivity(intent);
                break;
            case "movie":
                intent = new Intent(this, DetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.movie_popular_more:
                intent = new Intent(this, VerticalMovieListActivity.class);
                intent.putExtra("header", CONSTANTS.POPULAR_TAG);
                startActivity(intent);
                break;
            case R.id.movie_top_rated_more:
                intent = new Intent(this, VerticalMovieListActivity.class);
                intent.putExtra("header", CONSTANTS.TOP_RATED_TAG);
                startActivity(intent);
                break;
            case R.id.movie_upcoming_more:
                intent = new Intent(this, VerticalMovieListActivity.class);
                intent.putExtra("header", CONSTANTS.UPCOMING_TAG);
                startActivity(intent);
                break;
            case R.id.tv_popular_more:
                intent = new Intent(this, VerticalTvListActivity.class);
                intent.putExtra("header", CONSTANTS.POPULAR_TAG);
                startActivity(intent);
                break;
            case R.id.tv_top_rated_more:
                intent = new Intent(this, VerticalTvListActivity.class);
                intent.putExtra("header", CONSTANTS.TOP_RATED_TAG);
                startActivity(intent);
                break;
            case R.id.tv_upcoming_more:
                intent = new Intent(this, VerticalTvListActivity.class);
                intent.putExtra("header", CONSTANTS.UPCOMING_TAG);
                startActivity(intent);
                break;
        }
    }
}
