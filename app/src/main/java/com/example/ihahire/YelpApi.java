package com.example.ihahire;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {

    @GET("businesses/detail")
    Call<YelpBusinessesSearchResponse> getProducts(
            @Query("categories") String categories,
            @Query("name") String name


    );
}