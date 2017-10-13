package com.example.rynel.flickrtest.data;

import com.example.rynel.flickrtest.Flickr;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rynel on 10/13/2017.
 */

public class RemoteDataSource {

    //flickr URL
    public static final String BASE_URL =
            "http://farm5.staticflickr.com/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    //Retrofit construct
    public static Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

    public static Observable<List<Flickr>> getFlickrs(){

        Retrofit retrofit = create();
        RemoteService remoteService = retrofit.create(RemoteService.class);

        return remoteService.getFlickers();


    }
}

//TODO: fixed crashing but layout adapter not attached, so screen isn't populating