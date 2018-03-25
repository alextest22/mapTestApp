package com.example.alexdev.maptestapp.models.http.api;

import com.example.alexdev.maptestapp.models.http.models.ResponseGeoCode;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexdev on 02.11.17.
 */

public interface ApiGeoCode {

    @GET("geocode/json")
    Observable<ResponseGeoCode> getAddress(@Query("address") String address, @Query("key") String key);


}
