package com.telenorgp.tmdbgp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {FavouriteEntity.class}, version = 1)
public abstract class FavouriteDatabase extends RoomDatabase {
    private static FavouriteDatabase INSTANCE;
    public abstract FavouriteEntityDao favouriteEntityDao();
}
