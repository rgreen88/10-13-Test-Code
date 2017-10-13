package com.example.rynel.flickrtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rynel.flickrtest.data.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //declaring objects as per RecyclerView (layout/item animator), DatabaseHelper, and adapter
    RecyclerView recyclerView;
    List<Flickr> flickrsList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private FlickrAdapter flickrAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvFlickr);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();

        //set adapter
        recyclerView.setAdapter(flickrAdapter);
        recyclerView.setLayoutManager(layoutManager);

        RemoteDataSource.getFlickrs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Flickr>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        Log.d(TAG, "onSubscribe: ");

                    }

                    @Override
                    public void onNext(@NonNull List<Flickr> flickrList) {

                        Log.d(TAG, "onNext: " +flickrList.size());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        Log.d(TAG, "onError: " + e.toString());

                    }

                    @Override
                    public void onComplete() {

                        Log.d(TAG, "onComplete: ");

                        flickrAdapter = new FlickrAdapter(flickrsList);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(itemAnimator);
                        recyclerView.setAdapter(flickrAdapter);

                    }
                });
    }
}
