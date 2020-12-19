package com.telenorgp.tmdbgp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.telenorgp.tmdbgp.Utils.CONTRACT;

import java.util.List;


@Dao
public interface FavouriteEntityDao {
    @Query("SELECT * FROM " + CONTRACT.FAVOURITES_TABLE_NAME)
    List<FavouriteEntity> getAll();

    @Query("SELECT * FROM " + CONTRACT.FAVOURITES_TABLE_NAME + " WHERE " + CONTRACT.FAVOURITES_TYPE + " = (:type)")
    List<FavouriteEntity> getSpecific(String type);

    @Insert
    void insert(FavouriteEntity minifiedEntity);

    @Delete
    void delete(FavouriteEntity minifiedEntity);
}
