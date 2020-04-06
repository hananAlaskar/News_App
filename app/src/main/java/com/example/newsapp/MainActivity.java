package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private NewsAdapter mAdapter;

    private static final String THE_GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?q=debates&api-key=f90e137e-8881-4edd-ba02-3e6dd566cbb1";

    private static final int NEWS_LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (checkInternetConnection()) {

            getSupportLoaderManager().initLoader(NEWS_LOADER_ID, null, this).forceLoad();

        } else {

            findViewById(R.id.loading_indicator).setVisibility(View.GONE);
            ((TextView)(findViewById(R.id.empty_list))).setText(R.string.no_internet_connection);
            findViewById(R.id.empty_list).setVisibility(View.VISIBLE);
        }


    }

    private boolean checkInternetConnection() {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

       return ((networkInfo != null) && (networkInfo.isConnected()));

    }


    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {


        return new NewsLoader(this, THE_GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsList) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        updateUi(newsList);
    }


    private void updateUi(List<News> newsList) {

        if (newsList != null && !newsList.isEmpty()) {

            findViewById(R.id.main_view).setVisibility(View.VISIBLE);
            displayNews(newsList);

        }else {
            ((TextView)(findViewById(R.id.empty_list))).setText(R.string.no_news);
            findViewById(R.id.empty_list).setVisibility(View.VISIBLE);
        }


    }

    private void displayNews(List<News> newsList) {

        displayListViewPoliticsSection(getPoliticalNews(newsList));
        displayListViewFootballSection(getUSNews(newsList));
        displayListViewUSNewsSection(getFootballNews(newsList));
    }

    private void displayListViewPoliticsSection(List<News> newsList) {

        ListView politicsNewsSection_lv = findViewById(R.id.politics_section_lv);
        mAdapter = new NewsAdapter(this, R.id.politics_section_lv, newsList);
        politicsNewsSection_lv.setAdapter(mAdapter);

    }

    private void displayListViewUSNewsSection(List<News> newsList) {

        ListView usNewsSection_lv = findViewById(R.id.us_news_section_lv);
        mAdapter = new NewsAdapter(this, R.id.us_news_section_lv, newsList);
        usNewsSection_lv.setAdapter(mAdapter);

    }

    private void displayListViewFootballSection(List<News> newsList) {

        ListView footballNewsSection_lv = findViewById(R.id.football_section_lv);
        mAdapter = new NewsAdapter(this, R.id.football_section_lv, newsList);
        footballNewsSection_lv.setAdapter(mAdapter);

    }


    private List<News> getPoliticalNews(List<News> newsList) {

        List<News> politicalNews = new ArrayList<>();

        for (News news : newsList)
            if (news.getmSectionType().equals(getString(R.string.politics)))
                politicalNews.add(news);

        return politicalNews;
    }


    private List<News> getUSNews(List<News> newsList) {

        List<News> usNews = new ArrayList<>();

        for (News news : newsList)
            if (news.getmSectionType().equals(getString(R.string.us_news)))
                usNews.add(news);

        return usNews;
    }

    private List<News> getFootballNews(List<News> newsList) {

        List<News> footballNews = new ArrayList<>();

        for (News news : newsList)
            if (news.getmSectionType().equals(getString(R.string.football)))
                footballNews.add(news);

        return footballNews;
    }


    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        mAdapter.clear();
    }
}
