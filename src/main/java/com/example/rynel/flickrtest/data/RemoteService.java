package com.example.rynel.flickrtest.data;

import com.example.rynel.flickrtest.Flickr;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by rynel on 10/13/2017.
 */

public interface RemoteService {

    @GET("4461/37629819106_92c7b109da_m.jpg")
    Observable<List<Flickr>> getFlickers();

}
