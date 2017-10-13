package com.example.rynel.flickrtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rynel on 10/13/2017.
 */

public class FlickrAdapter extends RecyclerView.Adapter<FlickrAdapter.ViewHolder> {

    //array creation
    List<Flickr> flickrList = new ArrayList<>();
    Context context;

    public FlickrAdapter(List<Flickr> flickrList) {


        this.flickrList = flickrList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_flickr, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //updates flickr view from flickr class
        Flickr flickr = flickrList.get(position);
        holder.authorName.setText(flickr.getAuthor());

        Glide.with(context).load(flickr.getImageURL()).into(holder.flickrImageURL);

    }

    @Override
    public int getItemCount() {
        return flickrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView flickrImageURL;
        TextView flickrTitle, authorName;


        public ViewHolder(View itemView) {
            super(itemView);

            flickrTitle = itemView.findViewById(R.id.tvFlickr);
            flickrImageURL = itemView.findViewById(R.id.ivImageView);
            authorName = itemView.findViewById(R.id.tvAuthor);


        }

    }
}