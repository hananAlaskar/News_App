package com.example.newsapp;

import android.content.Context;

import com.example.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class NewsLoader extends AsyncTaskLoader<List<News>> {


    private String mUrl;

    public NewsLoader(@NonNull Context context, String url) {
        super(context);

        mUrl = url;
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<News> news = QueryUtils.fetchEarthquakeData(mUrl);
        return news;
    }


}
