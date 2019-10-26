package com.example.ihahire.networks;

import com.example.ihahire.models.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {

    @GET("businesses/search")
    Call<Search> getProducts(
            @Query("location") String location,
            @Query("term") String term

    );
}