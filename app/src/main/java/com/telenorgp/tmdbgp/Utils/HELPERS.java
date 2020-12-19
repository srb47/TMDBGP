package com.telenorgp.tmdbgp.Utils;


import com.telenorgp.tmdbgp.Database.FavouriteEntity;
import com.telenorgp.tmdbgp.Entities.DetailMovie;
import com.telenorgp.tmdbgp.Entities.DetailTv;
import com.telenorgp.tmdbgp.Entities.Genre;



public class HELPERS {
    public static FavouriteEntity convert(DetailMovie movie) {
        FavouriteEntity minifiedEntity = new FavouriteEntity();
        minifiedEntity.setName(movie.getTitle());
        minifiedEntity.setId(movie.getId().toString());
        minifiedEntity.setOverview(movie.getOverview());
        minifiedEntity.setPosterPath(movie.getPosterPath());
        minifiedEntity.setReleaseDate(movie.getReleaseDate());
        minifiedEntity.setStatus(movie.getStatus());
        StringBuilder builder = new StringBuilder("");
        for(Genre genre: movie.getGenres()) {
            builder.append(genre.getName()+",");
        }

        String genres = builder.toString().substring(0, builder.length()-1);
        minifiedEntity.setGenres(genres);
        return minifiedEntity;
    }

    public static FavouriteEntity convert(DetailTv tv) {
        FavouriteEntity minifiedEntity = new FavouriteEntity();
        minifiedEntity.setName(tv.getName());
        minifiedEntity.setId(tv.getId().toString());
        minifiedEntity.setOverview(tv.getOverview());
        minifiedEntity.setPosterPath(tv.getPosterPath());
        minifiedEntity.setReleaseDate(tv.getFirstAirDate());
        minifiedEntity.setStatus(tv.getStatus());

        StringBuilder builder = new StringBuilder("");
        for(Genre genre: tv.getGenres()) {
            builder.append(genre.getName()+",");
        }

        String genres = builder.toString().substring(0, builder.length()-1);
        minifiedEntity.setGenres(genres);
        return minifiedEntity;
    }

//    public static MinifiedEntity convertMovie(Movie movie) {
//        MinifiedEntity minifiedEntity = new MinifiedEntity();
//        minifiedEntity.setName(movie.getTitle());
//        minifiedEntity.setId(movie.getId().toString());
//        minifiedEntity.setOverview(movie.getOverview());
//        minifiedEntity.setPosterPath(movie.getPosterPath());
//        minifiedEntity.setReleaseDate(movie.getReleaseDate());
//        minifiedEntity.setStatus("");
//        minifiedEntity.setGenres(movie.getGenres().toString());
//        return minifiedEntity;
}


