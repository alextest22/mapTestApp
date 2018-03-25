package com.example.alexdev.maptestapp.models.geoCodeModel;

import android.content.Context;
import android.util.Log;

import com.example.alexdev.maptestapp.R;
import com.example.alexdev.maptestapp.models.http.Client;
import com.example.alexdev.maptestapp.models.http.api.ApiGeoCode;
import com.example.alexdev.maptestapp.models.http.models.ResponseGeoCode;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alexdev on 24.03.18.
 */

public class GeoCodeModel {


    public static void getData(String input, Context context, Observer<ResponseGeoCode> geoCodeObserver){
        Client.getClient(context)
                .create(ApiGeoCode.class)
                .getAddress(input,context.getString(R.string.google_api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(geoCodeObserver);
    }

}
