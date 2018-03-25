package com.example.alexdev.maptestapp.models.directionsModel;

import android.content.Context;
import android.util.Log;

import com.example.alexdev.maptestapp.R;
import com.example.alexdev.maptestapp.models.http.Client;
import com.example.alexdev.maptestapp.models.http.api.ApiDirections;
import com.example.alexdev.maptestapp.models.http.api.ApiGeoCode;
import com.example.alexdev.maptestapp.models.http.models.ApiResponseDirections;
import com.example.alexdev.maptestapp.models.http.models.ResponseGeoCode;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alex on 25.03.18.
 */

public class DirectionsModel {

    public static void getData( String origin,String destination,String waypoints,
                                Context context,
                                Observer<ApiResponseDirections> geoCodeObserver){

        Client.getClient(context)
                .create(ApiDirections.class)
                .getWay(origin,destination,waypoints,context.getString(R.string.google_api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(geoCodeObserver);
    }

}
