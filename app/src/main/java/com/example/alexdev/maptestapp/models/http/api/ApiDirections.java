package com.example.alexdev.maptestapp.models.http.api;

import com.example.alexdev.maptestapp.models.http.models.ApiResponseDirections;
import com.example.alexdev.maptestapp.models.http.models.ResponseGeoCode;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexdev on 02.11.17.
 */

public interface ApiDirections {

    @GET("directions/json")
    Observable<ApiResponseDirections> getWay(@Query("origin") String origin,
                                                 @Query("destination") String destination,
                                                 @Query("waypoints") String waypoints,
                                                 @Query("key") String key);


}
