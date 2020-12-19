package com.telenorgp.tmdbgp.Services;


import com.telenorgp.tmdbgp.Entities.MultiSearch;
import com.telenorgp.tmdbgp.Entities.WrapperCast;
import com.telenorgp.tmdbgp.Entities.WrapperImage;
import com.telenorgp.tmdbgp.Entities.WrapperVideo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface CommonService {
    @GET("{type}/{id}/videos")
    Call<WrapperVideo> getVideos(@Path("type") String type, @Path("id") String id, @Query("api_key") String apiKey);

    @GET("{type}/{id}/images")
    Call<WrapperImage> getImages(@Path("type") String type, @Path("id") String id, @Query("api_key") String apiKey);

    @GET("search/multi")
    Call<MultiSearch> getSearchResult(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") String page);

    @GET("{type}/{id}/credits")
    Call<WrapperCast> getCastMovie(@Path("type") String type, @Path("id") String id, @Query("api_key") String apiKey);
}
