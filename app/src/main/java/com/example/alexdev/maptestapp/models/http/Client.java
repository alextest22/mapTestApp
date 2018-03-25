package com.example.alexdev.maptestapp.models.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.alexdev.maptestapp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexdev on 23.03.18.
 */

public class Client {

        private static final int MAX_RETRY = 3;

        private static Retrofit retrofit      = null;


        public static Retrofit getClient(Context context, @NonNull Gson gsonCustom) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(context.getResources().getString(R.string.url_base))
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gsonCustom))
                    .build();

            return retrofit;
        }

        public static Retrofit getClient(Context context) {
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .setPrettyPrinting()
                    .setLenient()
                    .create();
            return getClient(context,gson);
        }






}
